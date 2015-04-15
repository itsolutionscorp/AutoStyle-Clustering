class Bob:
    def hey(self, str):
        if str.isupper():
            return 'Woah, chill out!'
        elif str.endswith('?'):
            return 'Sure.'
        elif str == '' or str.isspace():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
