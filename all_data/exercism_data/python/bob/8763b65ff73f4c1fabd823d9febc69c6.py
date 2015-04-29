#coding:utf-8

import string


class Bob(object):

    def _question(self, text):
        match = text.upper() == ''.join([letter for letter in text
                                         if letter not in string.digits])
        return 'Sure.' if text[-1] == '?' and not match else None

    def _yell(self, text):
        text_ = ''.join([letter for letter in text[:]
                         if letter not in string.digits])
        match = text_.upper() == text_
        has_letters = len([letter for letter in text_
                           if letter in string.uppercase]) > 0
        return 'Woah, chill out!' if match and has_letters else None

    def _nothing(self, text):
        return 'Fine. Be that way!' if not text.strip() else None

    def _anything_else(self, text):
        return 'Whatever.'

    _responses = (_nothing, _question, _yell, _anything_else)

    def hey(self, text):
        for response_function in self._responses:
            response = response_function(self, text)
            if response:
                return response
