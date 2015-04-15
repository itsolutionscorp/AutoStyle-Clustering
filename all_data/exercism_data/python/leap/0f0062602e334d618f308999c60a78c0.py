#!/usr/bin/env python
# encoding: utf-8

'''
# Exercism: Leap

Write a program that will take a year and report if it is a leap year.

The tricky thing here is that a leap year occurs:

```plain
on every year that is evenly divisible by 4
  except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
```

For example, 1997 is not a leap year, but 1996 is.  1900 is not a leap
year, but 2000 is.

If your language provides a method in the standard library that does
this look-up, pretend it doesn't exist and implement it yourself.

## Notes

For a delightful, four minute explanation of the whole leap year
phenomenon, go watch [this youtube video][video].

[video]: http://www.youtube.com/watch?v=xX96xng7sAE


## Source

JavaRanch Cattle Drive, exercise 3 [view source](http://www.javaranch.com/leap.jsp)
'''

'''
@author: Alan Schmitz

license:
The MIT License (MIT)

Copyright (c) 2014 Alan Schmitz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
'''

import os
import sys
import argparse
import pdb

__version__ = '0.1'
__updated__ = '2014-09-25'

#=============================================================================
# class Leap:
#=============================================================================
class Leap:

    #=========================================================================
    # check: just copy the question for later processing
    #=========================================================================
    def check(self, year):

        if self.is_divisible_by_4(year):
            if self.is_divisible_by_100(year):
                if not self.is_divisible_by_400(year):
                    return False
            return True
        return False

    #=========================================================================
    # is_divisible_by_4
    #=========================================================================
    def is_divisible_by_4(self, year):

        return((year / 4 * 4) == year)

    #=========================================================================
    # is_divisible_by_100
    #=========================================================================
    def is_divisible_by_100(self, year):

        return((year / 100 * 100) == year)

    #=========================================================================
    # is_divisible_by_400
    #=========================================================================
    def is_divisible_by_400(self, year):

        return((year / 400 * 400) == year)

#=============================================================================
# class Leap:
#=============================================================================
def is_leap_year(year):
    leap = Leap()
    return leap.check(year)

#=============================================================================
# main:
#=============================================================================
if __name__ == '__main__':

    program_name = os.path.basename(sys.argv[0])
    program_version = 'v%s' % __version__
    program_build_date = __updated__
    program_version_message = '%%(prog)s %s (%s)' % (program_version, program_build_date)
    program_license = '''%s
    The MIT License (MIT)

    Copyright (c) 2014 Alan Schmitz

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
USAGE
''' % (program_name)

    try:
        parser = argparse.ArgumentParser(description=program_license,
                formatter_class=argparse.RawDescriptionHelpFormatter)

        parser.add_argument('year',
                metavar='N',
                nargs=1,
                type=int,
                help='year to check')

        parser.add_argument('-v', '--version',
                action='version',
                version=program_version_message)

        myargs = parser.parse_args()

    except Exception as e:
        raise(e)
        indent = len(program_name) * ' '
        sys.stderr.write(program_name + ': ' + repr(e) + '\n')
        sys.stderr.write(indent + ' for help use --help')
        sys.exit(-1)
    
    leap = Leap()
    if leap.check(myargs.year[0]):
        print(str(myargs.year[0]) + ': is a leap year')
    else:
        print(str(myargs.year[0]) + ': is not a leap year')

# vi:ts=4:sw=4:expandtab:ft=python:
