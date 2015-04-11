import re


class Bob:
    def hey(self, string):
        statement = Statement(string)
        if statement.is_empty():
            return 'Fine. Be that way!'
        if statement.is_shouting():
            return 'Woah, chill out!'
        if statement.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'

class Statement(object):
    def __init__(self, string):
        self.string = string

    def is_question(self):
        return self.string.endswith('?')

    def is_shouting(self):
        return self.string.isupper() and not self.string.islower()

    def is_empty(self):
        return self.string is None or self.string.strip() == ''
