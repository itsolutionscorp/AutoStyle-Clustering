# -*- coding:utf-8 -*-
import random
import string

ALNUM = string.letters + string.digits
DIGITS = string.digits

def generate_name():
    return "".join((
            random.choice(ALNUM),
            random.choice(ALNUM),         
            random.choice(DIGITS),
            random.choice(DIGITS),
            random.choice(DIGITS),
            ))

class Robot(object):
    def __init__(self):
        self.reset()

    def reset(self):
        self.name = generate_name()

        
                       
