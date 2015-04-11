# -*- coding: utf-8 -*-
import re

class Bob(object):

    def __init__(self):
        self.respond_to_aggresion = 'Woah, chill out!'
        self.respond_to_question = 'Sure.'
        self.respond_to_silence = 'Fine. Be that way!'
        self.respond_default = 'Whatever.'

    def hey(self, msg):
        if is_silence(msg):
            return self.respond_to_silence
        else:
            msg = msg.decode('utf-8', errors='ignore').encode('utf-8')
            if is_aggresive(msg):
                return self.respond_to_aggresion
            elif is_question(msg):
                return self.respond_to_question
            else:
                return self.respond_default

def is_silence(msg):
    return not msg or not msg.strip()

def is_aggresive(msg):
    return msg.isupper()

def mk_is_question():
    quest_rx = re.compile(r'\?.?$')
    def main(msg):
        return quest_rx.search(msg)
    return main

is_question = mk_is_question()
