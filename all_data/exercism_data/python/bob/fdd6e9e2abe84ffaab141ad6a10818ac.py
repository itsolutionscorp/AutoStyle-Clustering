import re

def is_question(statement):
    return statement.endswith('?')

def is_shouting(statement):
    return statement.isupper()

def is_empty(statement):
    return not statement or statement.isspace()

class Bob:
    def hey(self, statement):
        if is_empty(statement):
            return 'Fine. Be that way!'
        if is_shouting(statement):
            return 'Woah, chill out!'
        if is_question(statement):
            return 'Sure.'
        else:
            return 'Whatever.'
