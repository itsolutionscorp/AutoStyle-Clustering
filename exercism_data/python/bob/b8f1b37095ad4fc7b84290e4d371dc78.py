class Bob(object):
    def hey(self, s):
        def shout(s):
            text = ''.join(v for v in s if v.isalpha())
            return text.isupper()

        if (not s) or s.isspace():  # if nothing was said
            return 'Fine. Be that way!'
        elif shout(s):
            return 'Woah, chill out!'
        elif s[-1] == '?':  # Questions ends with question mark
            return 'Sure.'
        else:
            return 'Whatever.'
