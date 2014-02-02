TODO:
[] Fix Error thrown when Button 4 is pressed, something to do with the timer being cancelled multiple times.
[] Decide how we want the forklift controls to work (SEE NOTE 1)
[] Work out AutoRanger conversion so that its outputs are in inches
[] Debug functionalities of all programmed Subsystems
	[] Drive
	[] Catapult
	[] AutoRanger
[] Program Autonomous ***SEE DriveAndShootCommandGroup***
[] Finish TODO list
[] Find all //TODOs in code and do whatever they say

**************************************************************************************************************************
NOTE 1: While we may still need to find the correct way to associate a control with a given command, there is a better way 
to implement the DriveBump*.java clases.  We need to get rid of the timer task code and merely use the Command class 
setTimeout method. This will let the command execute for X milliseconds without the hassle of an additional timer thread.

What happens when a command times out?   Does the command "interrupted" method get called so we can clean up?

The AutoDriveToShotRangeCommand could be similarly refactored to get rid of the safety timer.
-RJA-
**************************************************************************************************************************

NOTE 2: I added a docs folder to the project to hold some of the more useful reference docs.  -RJA-
**************************************************************************************************************************

Operator Controls:

I suggest that we replace the check box that reverses the sense of the joystick controls with a button that toggles the 
direction each time it is pressed.  A check box is probably too slow and cumbersome under the pressure of competition.  
The button should probably be a co-pilot function so the pilot does not inadvertently toggele the drive direction.
-RJA-
**************************************************************************************************************************
