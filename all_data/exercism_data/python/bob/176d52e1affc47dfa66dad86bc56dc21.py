class Bob():
    def __init__(self):
        pass
    
    def hey(self, string):
        if not bool(string.strip()) or not string:
            return 'Fine. Be that way!'
        elif string.isupper():
            return 'Woah, chill out!'
        elif string[len(string) - 1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
