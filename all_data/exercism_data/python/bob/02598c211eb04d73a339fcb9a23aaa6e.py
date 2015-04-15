class Bob:
    def hey(self, s):
        if not s or s.isspace():
            return 'Fine. Be that way!'
        elif s.isupper():
            return 'Woah, chill out!'
        elif s[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
