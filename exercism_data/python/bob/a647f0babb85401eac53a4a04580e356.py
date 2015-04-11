class Bob():
    def hey(self, text):
        text = text.strip()
        
        if isSilence(text):
            answer = 'Fine. Be that way.'
        elif isShout(text):
            answer = 'Woah, chill out!'
        elif isQuestion(text):
            answer = 'Sure.'
        else:
            answer = 'Whatever.'

        return answer

def isSilence(text):
    return text == ''

def isShout(text):
    return text.isupper()

def isQuestion(text):
    return text.endswith('?')
