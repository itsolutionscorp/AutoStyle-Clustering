import re

def hey(words):
    text = TextQualifier(words);
    if text.isSilence():
        return "Fine. Be that way!"
    if text.isShouting():
        return "Woah, chill out!"
    if text.isQuestion():
        return "Sure."
    return "Whatever."

class TextQualifier:
    def __init__(self, words):
        self.lastSentence, self.punctuation = self._parse(words);

    def _parse(self, words):
	self.words = words.strip();
        if self.words == "":
            return "", ""
        parts = re.split("([?!.]+)", self.words);
        if (len(parts) == 1):
            return parts[0], ""
        if len(parts) >= 2 and parts[-1] == "": 
            return parts[-3] , parts[-2]
        return parts[-2], ""

    def isSilence(self):
        return self.words == "";

    def isShouting(self):
        if (self.words.isupper() or self.lastSentence.isupper()):
            return True;
        return False;
    
    def isQuestion(self):
        if (self.isShouting()):
	    return False;
        if (self.punctuation.endswith("?")):
	    return True;
	return False;
