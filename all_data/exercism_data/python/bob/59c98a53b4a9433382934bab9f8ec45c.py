#!/usr/bin/python
# -*- coding: utf-8 -*-

import string

class AnsweringService(object):

    def __init__(self, what):
        self.what = what.encode('utf-8')
        self.preprocess()

    def replace_dict(self):
        return {
            'ä': 'a',
            'Ü': 'U',
        }

    def preprocess(self):
        self.what = self.what.strip()
        # This is bad, but also why I'm doing exercism :)
        for bad, replace_with in self.replace_dict().items():
            self.what = self.what.replace(bad, replace_with)

    def is_empty(self):
        return len(self.what) == 0

    def is_question(self):
        answer = False
        if not self.is_empty():
            answer = self.what.endswith('?')
        return answer

    def is_yelling(self):
        only_letters = ''.join([x for x in self.what if x in string.ascii_letters])
        if not only_letters:
            return False
        return only_letters.upper() == only_letters

    def answer(self):
        if self.is_empty():
            return 'Fine. Be that way!'
        if self.is_yelling():
            return 'Whoa, chill out!'
        if self.is_question():
            return 'Sure.'
        return 'Whatever.'


def hey(what):
    return AnsweringService(what).answer()
