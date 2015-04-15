#!/usr/bin/env python
# encoding: utf-8

'''
Bob - exercism 

@author: Alan Schmitz

# Bob description:
Bob is a lackadaisical teenager. In conversation, his responses are very limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.

## Instructions

Run the test file, and fix each of the errors in turn. When you get the
first test to pass, go to the first pending or skipped test, and make
that pass as well. When all of the tests are passing, feel free to
submit.

Remember that passing code is just the first step. The goal is to work
towards a solution that is as readable and expressive as you can make
it.

Please make your solution as general as possible. Good code doesn't just
pass the test suite, it works with any input that fits the
specification.

Have fun!



## Source

Inspired by the 'Deaf Grandma' exercise in Chris Pine's Learn to Program tutorial. [view source](http://pine.fm/LearnToProgram/?Chapter=06)

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
import string

__version__ = '0.1'
__updated__ = '2014-09-24'

#=============================================================================
# class bob:
#=============================================================================
class Bob:

    #=========================================================================
    # initializer: just copy the question for later processing
    #=========================================================================
    def __init__(self, question):

        self.question = question

    #=========================================================================
    # process: process the question asked (if any)
    # requirements:
    # 1) on a question: response is 'Sure.'
    # 2) if yelled at:  response is 'Whoa, chill out!'
    # 3) ask nothing:   response is 'Fine. Be that way!'
    # 4) anything else: response is 'Whatever.'
    #=========================================================================
    def process(self):

        words = self.question.split()

        # condition 3: nothing asked if number of arguments is 1
        if (len(self.question) == 0) or (len(words) == 0):

            return 'Fine. Be that way!'

        # check if we are being yelled at...
        if self.is_all_caps(words) and self.any_words(words):

            return 'Whoa, chill out!'

        # check if a question is asked i.e. ?
        if self.question[-1] == '?':

            return 'Sure.'

        # remaining response...
        return 'Whatever.'

    #=========================================================================
    # is_all_caps: test if all of the words are capitalized
    #=========================================================================
    def is_all_caps(self, words):

        for word in words:

            u_word = unicode(word)

            if u_word != u_word.upper():
                
                return False

        return True

    #=========================================================================
    # any_words: test if there are any words
    #=========================================================================
    def any_words(self, words):

        non_word_count = 0

        for word in words:

            letter_count = 0

            for letter in word:

                if letter not in string.ascii_letters:

                    letter_count += 1

            if letter_count == len(word):

                non_word_count += 1

        return(non_word_count != len(words))


#=========================================================================
# hey: ask the question to Bob
#=========================================================================
def hey(question):

    bob = Bob(question)
    rsp = bob.process()
    return rsp

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

        parser.add_argument('-q', '--question',
                action='store',
                dest='question',
                default='',
                help='question to ask Bob')

        parser.add_argument('-v', '--version',
                action='version',
                version=program_version_message)

        myargs = parser.parse_args()

    except Exception, e:
        raise(e)
        indent = len(program_name) * ' '
        sys.stderr.write(program_name + ': ' + repr(e) + '\n')
        sys.stderr.write(indent + ' for help use --help')
        sys.exit(-1)

    bob = Bob(myargs.question)
    rsp = bob.process()
    print 'Response: ' + rsp

# vi:ts=4:sw=4:expandtab:ft=python:
