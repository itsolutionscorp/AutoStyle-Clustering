class Bob:
    def hey(self, s):
        if s.replace(' ', '') == "":
            return 'Fine. Be that way!'
        elif s == s.upper() and s != s.lower():
            return 'Woah, chill out!'
        elif s[-1] == "?":
            return 'Sure.'
        return 'Whatever.'
