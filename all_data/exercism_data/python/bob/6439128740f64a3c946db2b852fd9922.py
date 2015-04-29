# -*- coding: utf-8 -*-
import re

class Bob:

    def __init__(self, ):
        self.empty_rx = re.compile(r'^\s*$')
        self.quest_rx = re.compile(r'\?.?$')
        self.respond_to_aggresion = 'Woah, chill out!'
        self.respond_to_question = 'Sure.'
        self.respond_to_silence = 'Fine. Be that way!'
        self.respond_default = 'Whatever.'

    def hey(self, msg):
        if self.is_silence(msg):
            return self.respond_to_silence
        else:
            msg = msg.decode('utf-8', errors='ignore').encode('utf-8')
            if self.is_aggresive(msg):
                return self.respond_to_aggresion
            elif self.is_question(msg):
                return self.respond_to_question
            else:
                return self.respond_default

    def is_silence(self, msg):
        return msg is None or self.empty_rx.match(msg)

    def is_aggresive(self, msg):
        return msg.isupper()

    def is_question(self, msg):
        return self.quest_rx.search(msg)
