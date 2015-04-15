#!/usr/bin/python

import string
import random

class Robot:
    def __init__(self):
        self.name = Robot._random_name()
        
    def reset(self):
        self.name = Robot._random_name()
    
    @staticmethod
    def _random_name():
        name = ''
        name += random.choice(string.uppercase) + random.choice(string.uppercase)
        name += random.choice(string.digits) + random.choice(string.digits) + random.choice(string.digits)
        return name
