class Bob(object):

    def hey(self, question):
        if not question.strip():
            return 'Fine. Be that way!'
        elif question.isupper():
            return 'Woah, chill out!'
        elif question[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
