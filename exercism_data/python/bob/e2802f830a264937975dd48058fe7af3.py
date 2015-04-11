class Bob(object):
    def hey(self, s):
        if (not s) or s.isspace():  # if nothing was said
            return 'Fine. Be that way!'
        elif s.isupper():
            return 'Woah, chill out!'
        elif s[-1] == '?':  # Questions ends with question mark
            return 'Sure.'
        else:
            return 'Whatever.'
