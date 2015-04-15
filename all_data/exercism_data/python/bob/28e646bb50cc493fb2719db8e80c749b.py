import re

class Bob:
    def _yelled_at(self, text):
        return re.search(ur"^[^a-z\xe4]*[A-Z\xdc\xc4\xdc]+[^a-z\xe4]*$", text, re.UNICODE)
    
    def _addressed_without_saying_anything(self, text):
        return re.search(r"^\s*$", text)
        
    def _asked_a_question(self, text):
        return re.search(r"\?$", text)
    
    def hey(self, text):
        if self._addressed_without_saying_anything(text):
            return "Fine. Be that way!"
        elif self._yelled_at(text):
            return "Woah, chill out!"
        elif self._asked_a_question(text):
            return "Sure."
        else:
            return "Whatever."
