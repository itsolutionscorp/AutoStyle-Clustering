class Bob(object):

    def hey(self, what):

        what = what.strip()

        if not what:
            return 'Fine. Be that way!'
        elif what.isupper():
            return 'Woah, chill out!'
        elif what.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
