#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import unittest
import unittest.mock
import datetime
import sys

def basic(n,m=0):
    return 'hello world {} and {}'.format(n, m//4)

def second_basic(n,m=0):
    return basic('x' + str(n), m)

class BasicTests(unittest.TestCase):
    def testOne(self):
        self.assertEqual(basic(1), 'hello world 1 and 0')

    def testTypeError(self):
        with self.assertRaises(TypeError):
            basic(None, 'not a number')

    def testSecondCall(self):
        self.assertEqual(second_basic(1), 'hello world x1 and 0')

    def testMockedSecondCall(self):
        second_basic = unittest.mock.MagicMock(return_value='hello mocky')
        self.assertEqual(second_basic(1), 'hello mocky')

def main():
    # actual runner.
    print(basic(None))
    print(basic(datetime.datetime.now()))
    print(basic(True, 7))

if __name__ == '__main__':
    # add a cmdline arg to run tests; that allows this to be self-contained.
    if len(sys.argv) > 1 and sys.argv[1] == 'test':
        # pop that arg because unittest reads the args
        sys.argv.pop(1)
        print(sys.argv)
        unittest.main()
    else:
        main()

