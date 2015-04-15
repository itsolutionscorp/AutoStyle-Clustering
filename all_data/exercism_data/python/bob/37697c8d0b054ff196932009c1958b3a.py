class Bob:

    """Bob responds, albeit tersely"""
    def hey(self, statement):
        if not statement or statement.isspace():
            return 'Fine. Be that way!'
        if statement.isupper():
            return 'Woah, chill out!'
        elif statement.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
