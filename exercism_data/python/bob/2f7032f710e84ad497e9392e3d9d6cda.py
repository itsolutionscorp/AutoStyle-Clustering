class Bob():
    def __init__(self):
        pass
    
    def hey(self, comment):
        if comment.isupper():
            return 'Woah, chill out!'
        elif comment.strip() == '':
            return "Fine. Be that way!"
        elif comment[-1] == "?":
            return 'Sure.'
        else:
            return "Whatever."
