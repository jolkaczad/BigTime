
I. PROJECT GOALS
----------------

    Investigate how the lifecycle goes in common situations for an Activity.

    Check how to safely save a state of an Activity with OnRestoreInstanceState and
    OnSaveInstanceState.



II. PROBLEMS ENCOUNTERED
------------------------

    1. Had problems with printing out a string through the LOG. Turns out
    you cannot send multiple newline ('\n') characters through the LOG. The
    printing will terminate at the point where these multiple newlines occur.

    2.

III. LESSONS LEARNED
--------------------
    0. Summary:
    Activity is destroyed when:
        - Back button is pressed
        - Screen is rotated

    Widget states are not saved when object is destroyed
    1.
    Application is first started, functions are called in order:
        - Constructor
        - OnCreate
        - OnStart
        - OnResume

    2.
    Back button pressed:
        - OnPause
        - OnStop
        - OnDestroy
    The Activity is no more

    3.
    Screen goes blank on an active Activity:
        - OnPause
        - OnSaveInstanceState
        - OnStop

    4.
    Wake up from sleeping (3):
        - OnRestart
        - OnStart
        - OnResume
    Object hasn't been destroyed. All variables still hold their values.

    5.
    Home button pressed:
        - OnPause
        - OnSaveInstanceState
        - OnStop

    6.
    Resume a minimized application:
        - OnRestart
        - OnStart
        - OnResume

    7.
    Rotate screen:
        - OnPause
        - OnSaveInstanceState
        - OnStop
        - OnDestroy
    Object is no more!
        - Constructor
        - OnCreate
        - OnStart
        - OnRestoreInstanceState
        - OnResume

    8.
    Stopped (inactive) application is killed from task manager:
        - OnDestroy

IV. IDEAS
---------

    1.