#!/usr/bin/env python
#encoding:utf-8


class Bob(object):
    def __init__(self):
        super(Bob, self).__init__()

    def hey(self, message):
        if message:
            if message.isspace():
                return self._anwser_nothing()
            elif message.isupper():
                return self._anwser_yell()
            elif message.endswith('?'):
                return self._anwser_question()
            else:
                return self._anwser_others()
        else:
            return self._anwser_nothing()

    def _anwser_question(self):
        return 'Sure.'

    def _anwser_yell(self):
        return 'Woah, chill out!'

    def _anwser_nothing(self):
        return 'Fine. Be that way!'

    def _anwser_others(self):
        return 'Whatever.'
