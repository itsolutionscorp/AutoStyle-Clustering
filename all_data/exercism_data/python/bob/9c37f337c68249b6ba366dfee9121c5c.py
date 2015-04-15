class Bob:

    def hey(self, str):
        if str.isupper():
            return 'Woah, chill out!'
        elif len(str) > 1 and str[-1] == '?':
            return 'Sure.'
        elif len(str.strip()) == 0:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
