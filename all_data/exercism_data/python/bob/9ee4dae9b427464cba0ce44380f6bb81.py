class Bob():

    def hey(self, s):
        if not s or len(str(s).strip()) == 0:
            return 'Fine. Be that way!'
        elif s.upper() == s:
            return "Woah, chill out!"
        elif s.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
