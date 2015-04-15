class Speech:
    def __init__(self, strn):
        self.strn = strn
    
    def is_empty(self):
        return self.strn == None or self.strn.isspace() or len(self.strn) == 0
        
    def is_yell(self):
        return self.strn.isupper()

    def is_question(self):
        return  self.strn[-1] == "?"

class Bob:
    def hey(self, strn):
        speech = Speech(strn)
        if speech.is_empty():
            return 'Fine, be that way.'
        elif speech.is_yell():
            return 'Woah, chill out!'
        elif speech.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'
