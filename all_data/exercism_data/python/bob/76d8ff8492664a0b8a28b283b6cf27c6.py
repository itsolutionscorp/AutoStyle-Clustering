class Speech:
    def __init__(self, speech):
        self.speech = speech
    
    def is_empty(self):
        return self.speech == '' or self.speech.isspace()
        
    def is_yell(self):
        return self.speech.isupper()

    def is_question(self):
        return self.speech.endswith("?")

class Bob:
    def hey(self, speech):
        speech = Speech(speech)
        if speech.is_empty():
            return 'Fine, be that way.'
        elif speech.is_yell():
            return 'Woah, chill out!'
        elif speech.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'
