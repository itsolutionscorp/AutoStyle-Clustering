class Bob(object):
    def hey(self, str):
        str = str.strip()
        if len(str) == 0: # address Bob, without actually saying anything
            return 'Fine. Be that way!'
        elif str.isupper():
            return 'Woah, chill out!'
        elif str[-1] == '?': # Question
            return 'Sure.'
        else:
            return 'Whatever.'
