#!/usr/bin/python
"""This is the "bob" module: an excercism assignment.

To test:
 ./bob.py
"""

__author__ = "Neal McBurnett <http://neal.mcburnett.org/>"
__version__ = "0.1.0"
__date__ = "2014-07-24"

def hey(input):
        if len(input) == 0   or  input.isspace():
            # you addressed him without actually saying anything.
            return('Fine. Be that way!')
        elif input.upper() == input  and  input.lower() != input:
            # you yelled at him, using all caps (and at least some caps)
            return('Woah, chill out!')
        elif input[-1:] == '?':			
            # you asked a question.
            return('Sure.')
        else:
            # default
            return('Whatever.')

if __name__ == "__main__":
    import unittest

    suite = unittest.TestLoader().discover(".")
    unittest.TextTestRunner().run(suite)
