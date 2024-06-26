package com.example.myapplication.screen.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.PopUpMenuBinding
import com.example.myapplication.bottom_sheet_dialogs.PermissionDialog
import com.example.myapplication.databinding.DialogSavingTimeBinding
import com.example.myapplication.dialogs.AppDialog
import com.example.myapplication.dialogs.DialogFactory
import com.example.myapplication.dialogs.DialogResultManager
import com.example.myapplication.dialogs.Dialogs
import com.example.myapplication.dialogs.modals.EmailModal
import com.example.myapplication.manager.AppBarManager
import com.example.myapplication.manager.NavigationManager
import com.example.myapplication.manager.PopUpWindowManager
import com.example.myapplication.recycler_view_data_modals.PermissionModal
import com.example.myapplication.screen.add_block_section.AddRestrictionActivity
import com.example.myapplication.screen.faq_section.FAQActivity
import com.example.myapplication.screen.notification_section.BlockedNotificationActivity
import com.example.myapplication.screen.settings_section.SettingsActivity
import com.example.myapplication.screen.upgrade_section.UpgradeActivity
import com.example.myapplication.utility.*


class MainActivity :
    AppCompatActivity(),
    NavigationManager.TabChangeListener,
    AppBarManager.AppBarOptionListener,
DialogResultManager{


    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationManager: NavigationManager
    private lateinit var appBarManager: AppBarManager
    private lateinit var popUpWindowManager: PopUpWindowManager

    private var isPermissionDialogShown = false

    private val permissionModal :ArrayList<PermissionModal> by lazy {
        ArrayList()
    }

    private val savingDialogImplementor : AppDialog by lazy {
        DialogFactory.create(Dialogs.SAVING_TIME)
    }

    private val miuiDialogImplementor : AppDialog by lazy {
        DialogFactory.create(Dialogs.MIUI)
    }


    /**
     *  on create implementation for the main activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        window.setStatusBar(this, ContextCompat.getColor(this, R.color.white))
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        setClickListener()
        setContractUtils()
    }

    override fun onResume() {
        super.onResume()
        handlePermissions()

    }

    /**
     *  This function is used to only works when the permission list dialog is opened and user click on the
     *  any permission and go to the settings and from there come back again to the app , used to check the status
     *  for the permission and updating the permission list.
     *
     *  case handle ->  Check from the bit -> isPermissionDialogShown
     */
    private fun handlePermissions() {
        if(!isPermissionDialogShown)
            return

        permissionDialog.onCheckPermission(permissionModal, binding.permissionView){
            isPermissionDialogShown= false
        }
    }

    /**
     *  function which is used to initialize the notification contract which would be useful to
     *  ask the permission for the notification for the android version greater than or equal to the
     *  Android version 13
     */
    private fun setContractUtils() {
        ContractsUtils.apply {
            setNotificationContract(
                registerForActivityResult(ActivityResultContracts.RequestPermission()){

                    if(it)
                        return@registerForActivityResult

                    if(!shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS))
                        navigateToSettings()
                }
            )
        }
    }

    private fun setClickListener() {
        binding.apply {
            permissionView.btnGivePermission.setOnClickListener {
                isPermissionDialogShown = true
                permissionDialog.showDialog()
            }
        }
    }

    /**
     *  here we will initialize the different managers for the main screen.
     */
    private fun initialize() {
        appBarManager = AppBarManager(binding.appBar, this)
        popUpWindowManager = PopUpWindowManager(PopUpMenuBinding.inflate(layoutInflater), this)
        navigationManager = NavigationManager(binding.bottomBar, supportFragmentManager, this)

        // necessary function
        handlePermissionView()

        // initialize the app dialogs
        initAppDialogs()
    }

    private fun initAppDialogs() {
        savingDialogImplementor.apply {
            initializeDialog(DialogSavingTimeBinding.inflate(layoutInflater))
            register(this@MainActivity)
        }

        miuiDialogImplementor.apply {
            initializeDialog(DialogSavingTimeBinding.inflate(layoutInflater))
            register(this@MainActivity)
        }
    }

    /**
     *  This function is called when the main activity and useful for checking the necessary permission
     *  for the app is granted or not
     *  , and on the basis of the which the (Give Permission view) is shown or hide.
     */
    private fun handlePermissionView() {
        permissionModal.addDefaultPermission(this){permissionStatus->
            if(permissionStatus){
                binding.permissionView.root.visibility(View.GONE)
                return@addDefaultPermission
            }
            binding.permissionView.root.visibility(View.VISIBLE)

        }
    }

    /**
     *  callback function when any tab is clicked or selected by the user from the bottom nav bar
     *  except the 'Add Restriction tab (floating action button click)'
     */
    override fun onTabChanged(appBarTitle: String) {
        appBarManager.changeAppBarTitle(appBarTitle)
    }

    /**
     *  callback option when user click on the different option from the app bar of the screen.
     */
    override fun onOptionClicked(navigator: AppBarManager.Navigator) {
        val activity = when (navigator) {
            AppBarManager.Navigator.Upgrade -> UpgradeActivity::class.java
            AppBarManager.Navigator.Setting -> SettingsActivity::class.java
            AppBarManager.Navigator.FAQ -> FAQActivity::class.java
            AppBarManager.Navigator.Notification -> BlockedNotificationActivity::class.java
        }
        startActivity(Intent(this, activity))
    }


    /**
     *  function to show the rate us dialog
     */
    override fun showDialog() {
        savingDialogImplementor.show()
    }

    /**
     *  called when the user click on the more option icon from the app bar of the screen.
     */
    override fun showPopUpWindow(view: View) {
        popUpWindowManager.showPopUpWindow(view)
    }

    /**
     *  function when the user click on the add restriction (floating action button)
     *  from the bottom nav bar screen.
     */
    override fun addRestriction() {
        startActivity(Intent(this, AddRestrictionActivity::class.java))
    }

    /**
     *  variable for the permission dialog which is an bottom sheet dialog ,
     *  now permission have some permission -> Special Access permission and normal permission
     *  for that purpose we have the two callbacks
     *
     *  for special -> intent callback
     *  for normal  -> contract permission
     */
    private val permissionDialog :PermissionDialog by lazy {
        PermissionDialog(this, permissionModal, intentCallback = {
            startActivity(it)
        }, contractCallback = {
            ContractsUtils.getContract().launch(it)
        })

    }

    /**
     *  function is very very important , because in fragments attached to this activity ,
     *  sometime we need the requireActivity, function -> they can throw fragment not attached to
     *  the context , so in that case , we will use the activity (parent activity ) there because fragment may not
     *  attached to context, but activity still alive there.
     */
    companion object {
        private lateinit var instance: MainActivity
        fun getInstance() = instance
    }

    init {
        instance = this
    }

    override fun <T : Any> onResult(data: T) {
        when(data){
            is String ->{
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
            }

            is EmailModal ->{
                Toast.makeText(this, "email modal obtained ${data.content}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}