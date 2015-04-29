#! /usr/bin/env python

import re
from collections import OrderedDict


class Bob:
    vocab = OrderedDict()
    default_answer = 'Whatever.'
    
    def __init__(self):
        # Order is important
        self.vocab[re.compile('^(?:\s+)?$')] = 'Fine. Be that way!'
        self.vocab[re.compile('^[^a-z]+$')] = 'Woah, chill out!'
        self.vocab[re.compile('^.*\?$')] = 'Sure.'

    def hey(self, msg):
        if msg is None:
            msg = ""
        
        for rx, txt in self.vocab.iteritems():
            if rx.match(msg):
                return txt
       
        return self.default_answer
