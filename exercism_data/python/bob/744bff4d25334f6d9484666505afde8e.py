class Bob(object):
    def __init__(self):
        pass
    

    def hey(self, txt):
        if txt.isupper():
            return 'Woah, chill out!'
        elif txt.endswith("?"):
            return 'Sure.'
        elif not txt.strip():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
