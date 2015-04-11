#
# Skeleton file for the Python "Bob" exercise.
#

class Statement(object):
    def __init__(self, what):
        self.statement = what.strip()

    @property
    def is_shouting(self):
        return self.statement.isupper()

    @property
    def is_silence(self):
        return self.statement == ''

    @property
    def is_question(self):
        return self.statement.endswith('?')

def hey(what):
    statement = Statement(what)

    if statement.is_shouting:
        response = 'Whoa, chill out!'
    elif statement.is_silence:
        response = 'Fine. Be that way!'
    elif statement.is_question:
        response = 'Sure.'
    else:
        response = 'Whatever.'

    return response
