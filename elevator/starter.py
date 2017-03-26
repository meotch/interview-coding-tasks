#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# to run:
# ./starter.py test # run tests
# ./starter.py test --failfast # any unittest args, including single test
# ./starter.py test BasicTests.testTypeError # any unittest args, including single test
# ./starter.py # run the actual main()

import unittest
import unittest.mock
import datetime
import sys
import time

from Elevator import Elevator

class BasicTests(unittest.TestCase):
    def testElevatorStartsOnGroundFloor(self):
        elevator = Elevator()
        self.assertEqual(elevator.currentFloor, 0)

    def testPickupInRightOrder(self):
        elevator = Elevator(speed=0.5)
        elevator.handleRequest(floor=8)
        elevator.handleRequest(floor=4)
        time.sleep(1)
        elevator.handleRequest(floor=1)
        self.assertEqual(elevator.direction, 1)
        self.assertEqual(elevator.nextFloor, 4)

    def testShouldGoBackDown(self):
        elevator = Elevator()
        elevator.currentFloor = 4
        elevator.doorsOpen = True
        elevator.closeDoors()
        self.assertEqual(elevator.nextFloor, 0)
        self.assertEqual(elevator.direction, -1)


def main():
    unittest.main()

if __name__ == '__main__':
    # add a cmdline arg to run tests; that allows this to be self-contained.
    if len(sys.argv) > 1 and sys.argv[1] == 'test':
        # pop that arg because unittest reads the args
        sys.argv.pop(1)
        print(sys.argv)
        unittest.main()
    else:
        main()

