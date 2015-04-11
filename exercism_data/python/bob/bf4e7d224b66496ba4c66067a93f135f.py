#!/usr/bin/env python
# encoding: utf-8

'''
# Bob

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
'''

import os
import sys
import argparse
import string


# hey: ask the question to Bob
def hey(question):
    bob = Bob()
    return(bob.ask(question))


class Bob:

    # process: process the question asked (if any)
    # requirements:
    # 1) on a question: response is 'Sure.'
    # 2) if yelled at:  response is 'Whoa, chill out!'
    # 3) ask nothing:   response is 'Fine. Be that way!'
    # 4) anything else: response is 'Whatever.'
    def ask(self, question):
        words = question.split()

        # condition 3: nothing asked if number of arguments is 1
        if (len(question) == 0) or (len(words) == 0):
            return 'Fine. Be that way!'

        # check if we are being yelled at...
        if self.is_all_caps(words) and self.any_words(words):
            return 'Whoa, chill out!'

        # check if a question is asked i.e. ?
        if question[-1] == '?':
            return 'Sure.'

        # remaining response...
        return 'Whatever.'

    # is_all_caps: test if all of the words are capitalized
    def is_all_caps(self, words):
        for word in words:
            if sys.version_info >= (3, 0):
                u_word = str(word)
            else:
                u_word = unicode(word)
            if u_word != u_word.upper():
                return False
        return True

    # any_words: test if there are any words
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


if __name__ == '__main__':

    program_name = os.path.basename(sys.argv[0])

    try:
        parser = argparse.ArgumentParser(
                description=program_name,
                formatter_class=argparse.RawDescriptionHelpFormatter)

        parser.add_argument(
                '-q', '--question',
                action='store',
                dest='question',
                default='',
                help='question to ask Bob')

        my_args = parser.parse_args()

    except Exception as e:
        raise(e)
        indent = len(program_name) * ' '
        sys.stderr.write(program_name + ': ' + repr(e) + '\n')
        sys.stderr.write(indent + ' for help use --help')
        sys.exit(-1)

    bob = Bob()
    print('Response: ' + bob.ask(my_args.question))

# vi:ts=4:sw=4:expandtab:ft=python:
