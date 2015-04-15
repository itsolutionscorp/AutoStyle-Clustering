class Bob():

    def __init__(self):
        return None

    def hey(self, string):
        if(string and len(string) > 0):
            if(string.strip() == ''):
                return 'Fine. Be that way!'
            if(self.allUpperCase(string)):
                return 'Woah, chill out!'
            if(string[-1] == '?'):
                return 'Sure.'

            return 'Whatever.'
        else:
            return 'Fine. Be that way!'

    def allUpperCase(self, string):
        lettercount = 0
        for letter in string:
            if(letter.isalpha()):
                lettercount += 1
                if not(letter.isupper()):
                    return False
        if(lettercount == 0):
            return False
        return True
