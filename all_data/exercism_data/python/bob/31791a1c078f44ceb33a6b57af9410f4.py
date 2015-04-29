def hey(self):
    if self == '':
        return 'Fine. Be that way!'
    else:
        if self.isupper():
            return 'Whoa, chill out!'
        elif self[-1] == '?':
            return 'Sure.'
        elif self.isspace() and self.isalnum() != True:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
