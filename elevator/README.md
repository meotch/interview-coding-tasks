Your company has been asked to installed several elevators in the lobby of stanson head quarters. Since they are moving offices within the next 2 years they would like to have the program be reusable in the next building as well.

#Goal
Make sure that requirements are implemented and tests and all provided tests pass

#Requirements
* The elevator should work for any number of floors
* A passanger should be able to check the floor its currently on
* A passanger should be able to request a floor to go to
* People should be able to request the elevator to come to them
* The Elevator should pick up people in order of its current direction
    Example: if the elevator is on floor 2 and it gets a request from floor 8 first, and then floor 4. It should pick up floor 4 first

* The elevator can not change direction till all requests in its current direction have been satisfied
    Example: the elevator picks the person on floor 4 up, and then moves up. even if the person presses on 1

* If the elevator can drop somebody off on the way, it will do so
    Example: the person getting on at floor 4 wants to go to 6, this will be the next stop before it moves to 8

* When there is nothing left to do, the elevator should move back to the ground floor








