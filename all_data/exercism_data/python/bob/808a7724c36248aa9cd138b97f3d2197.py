import string

class Bob:
    def hey(self, hey):
        if not hey.strip():
            return 'Fine. Be that way!'

        if hey.isupper():
            return 'Woah, chill out!'

        if hey.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
