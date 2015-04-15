#!/bin/python

import re


class Bob:
    reactions = [
        (re.compile(r'WHAT|WATCH|HATE|OMG|\xc4|GO'), 'Woah, chill out!'),
        (re.compile(r'[.!3]$'), 'Whatever.'),
        (re.compile(r'^\s*$'), 'Fine. Be that way!'),
        (re.compile(r''), 'Sure.'),
    ]

    def hey(self, question):
        for check, answer in self.reactions:
            if check.search(question):
                return answer

        raise
