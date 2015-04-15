import string

class Bob:
    def hey(self, hey):
        if all(ch in string.whitespace for ch in hey):
            return 'Fine. Be that way!'

        if any(ch in string.letters for ch in hey):
            if not any(ch.islower() for ch in hey):
                return 'Woah, chill out!'

        if hey.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
