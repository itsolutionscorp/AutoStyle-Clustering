#! /usr/bin/env python

import re


class Bob:
    # vocabulary is a list of pairs regex -> answer
    vocab = []
  
    def __init__(self):
        # Order is important
        self.vocab.append((re.compile(r'^(?:\s+)?$'), 'Fine. Be that way!'))
        self.vocab.append((re.compile(r'^[^a-z]+$'),  'Woah, chill out!'))
        self.vocab.append((re.compile(r'^.*\?$'),     'Sure.'))
        # the last element is a default answer
        self.vocab.append((re.compile('.*'),         'Whatever.'))

    def hey(self, msg):
        if msg is None:
            msg = ""

        for rx, txt in self.vocab:
            if rx.match(msg):
                return txt
