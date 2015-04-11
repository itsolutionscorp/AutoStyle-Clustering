class Bob(object):

    def hey(self, expression):
        chars = [c for c in expression if c.isalpha()]
        if expression.strip() == '':
            return 'Fine. Be that way!'
        if chars and all(c.isupper() for c in chars):
            return 'Woah, chill out!'
        elif expression.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
