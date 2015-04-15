import re

class Bob:

    def hey(self, statement):
        if self.silence(statement):
            return 'Fine. Be that way!'
        elif self.yelling(statement):
            return 'Woah, chill out!'
        elif self.question(statement):
            return 'Sure.'
        else:
            return 'Whatever.'

    def yelling(self, statement):
        return statement == statement.upper() and re.search(r'[a-zA-Z]', statement)

    def question(self, statement):
        return statement[-1] == '?'

    def silence(self, statement):
        return statement.strip() == ''
