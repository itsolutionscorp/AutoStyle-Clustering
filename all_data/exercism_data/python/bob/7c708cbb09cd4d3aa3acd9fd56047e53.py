class Bob(object):
    def hey(self, input=''):
        if input is None or not input.strip():
            return 'Fine. Be that way!'
        elif input.upper() == input:
            return 'Woah, chill out!'
        elif input.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
