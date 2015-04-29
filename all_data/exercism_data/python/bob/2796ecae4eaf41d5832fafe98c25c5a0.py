__author__ = 'Daniel'


class Bob(object):
    def hey(self, statement):
        if statement.isupper():
            return "Woah, chill out!"
        elif statement.isspace() or statement == '':
            return "Fine. Be that way!"
        elif statement.rfind("?") == (len(statement)-1):
            return "Sure."
        else:
            return "Whatever."
