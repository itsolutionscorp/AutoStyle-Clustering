class Bob(object):
    def hey(self, speak):
        #silence
        if (speak.strip() == ''):
            return 'Fine. Be that way!'
        #shouting - all upper case letters
        elif (speak.isupper()):
            return 'Woah, chill out!'
        #is it a question
        elif (speak[-1] == "?"):
            return "Sure."
        # default answer
        else:
            return "Whatever."
