class Bob(object):
    "First python exercise in exercism"

    def __init__(self):
        "Null initializer because no class fields"
        pass

    def hey(self, text):
        "Eliza like response generator"
        if len(text.strip()) == 0:
            response = 'Fine. Be that way!'
        elif text.isupper():
            response = 'Woah, chill out!'
        elif text[-1] == '?':
            response = 'Sure.'
        else:
            response = 'Whatever.'

        return response
