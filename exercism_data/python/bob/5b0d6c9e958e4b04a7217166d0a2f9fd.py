#!/usr/bin/python

import re
import string


class Bob:
    def __init__(self):
        self.question_regex = re.compile(r'.*\?$')
        self.shouting_regex = re.compile(r'^[A-Z]+$')
        self.space_only_regex = re.compile(r'^\s+$')
        pass

    def hey(self, message):
        letters_only = re.sub('[\s0-9'+re.escape(string.punctuation)+']+', '', message)

        if self.shouting_regex.match(letters_only):
            return 'Woah, chill out!'
        elif self.question_regex.match(message):
            return "Sure."
        elif self.space_only_regex.match(message) or not message:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
