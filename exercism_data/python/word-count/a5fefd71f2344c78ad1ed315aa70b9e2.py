#!/usr/bin/env python
# encoding: utf-8

'''
# Word Count

Write a program that given a phrase can count the occurrences of each word in that phrase.

For example for the input `"olly olly in come free"`

```plain
olly: 2
in: 1
come: 1
free: 1
```



## Source

The golang tour [view source](http://tour.golang.org)
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

__version__ = '0.1'
__updated__ = '2014-09-25'

#=============================================================================
# class WordCount:
#=============================================================================
class WordCount:

    #=========================================================================
    # initializer: initialize an empty dictionary
    #=========================================================================
    def __init__(self):

        self.words = {}

    #=========================================================================
    # count: count the occurence of words in the list
    #=========================================================================
    def count(self, word_list):

        for word in word_list.split():

            if word in self.words:

                self.words[word] += 1

            else:

                self.words[word] = 1

        return self.words

#=========================================================================
# word_count: ask the question to Bob
#=========================================================================
def word_count(word_list):

    wc = WordCount()
    return wc.count(word_list)

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

        parser.add_argument('words',
                metavar='N',
                nargs=1,
                type=str,
                help='word list')

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

    wc = WordCount()
    print(wc.count(myargs.words[0]))

# vi:ts=4:sw=4:expandtab:ft=python:
