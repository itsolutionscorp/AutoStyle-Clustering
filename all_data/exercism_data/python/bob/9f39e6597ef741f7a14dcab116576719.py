class Bob():
    def hey(self, toBob):
        if not toBob or toBob.isspace():
            return 'Fine. Be that way!'
        elif toBob.isupper():
            return 'Woah, chill out!'
        elif toBob.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
