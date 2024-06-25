package com.example.myapplication.dialogs

import com.example.myapplication.dialogs.implementors.*

object DialogFactory {
    fun create(dialogs: Dialogs):BindingManager{
        return when(dialogs){
            Dialogs.MIUI-> MIUIDialogImplementor()
            Dialogs.SAVING_TIME-> SavingDialogImplementor()
            Dialogs.PAUSE_DELAY-> PauseDelayDialogImplementor()
            Dialogs.CUSTOM_QUOTE-> CustomQuoteDialogImplementor()
            Dialogs.WEEK-> StartOfWeekDialogImplementor()
            Dialogs.WEEKLY_USAGE_LIMIT-> WeeklyUsageLimitDialogImplementor()
            Dialogs.SET_USAGE_LIMIT_MULTIPLE_DAYS-> MultiDaysUsageDialogImplementor()
            Dialogs.THEME-> ThemeDialogImplementor()
            Dialogs.LANGUAGE-> ChangeLanguageDialogImplementor()
            Dialogs.BUG_REPORT-> BugReportDialogImplementor()
        }
    }
}