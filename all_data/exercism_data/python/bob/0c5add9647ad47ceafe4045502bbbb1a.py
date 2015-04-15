class Bob():
    def __init__(self):
        pass
    
    def hey(self, string):
#        elif:
        if not bool(string.strip()) or not string:
            return 'Fine. Be that way!'
        if string.isupper():
            return 'Woah, chill out!'
        elif string[len(string) - 1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
