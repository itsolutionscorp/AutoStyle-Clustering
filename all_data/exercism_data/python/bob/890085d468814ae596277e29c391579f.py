import re

class Bob:
    def _yelled_at(self, text):
        return text.isupper()

    def _addressed_without_saying_anything(self, text):
        return text == '' or text.isspace()
        
    def _asked_a_question(self, text):
        return text.endswith('?')
    
    def hey(self, text):
        if self._addressed_without_saying_anything(text):
            return "Fine. Be that way!"
        elif self._yelled_at(text):
            return "Woah, chill out!"
        elif self._asked_a_question(text):
            return "Sure."
        else:
            return "Whatever."
