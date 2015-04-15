import string
import random

class Robot:
    def __init__(self):
        self.letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
        self.num_letters = 2
        self.numbers = '0123456789'
        self.num_numbers = 3
        self.name = self.generate_name()
        pass

    def reset(self):
        self.name = self.generate_name()

    def generate_name(self):
        current = ''
        for i in xrange(0,self.num_letters):
            current += random.choice(self.letters)
        for i in xrange(0,self.num_numbers):
            current += random.choice(self.numbers)
        return current
