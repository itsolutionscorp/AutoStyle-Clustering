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
        self._words = words
        self._normalize();
        self._parse();

    def _normalize(self):
        self._words = self._words.strip();
        if self._words == "":
            return
        if (re.search("[?!.]$", self._words) == None):
            self._words += "."

    def _parse(self):
        self._lastSentence = ""
        self._punctuation = ""
        if self._words == "":
            return
        parts = re.split("([?!.]+)", self._words);
        self._lastSentence = parts[-3].strip()
        self._punctuation = parts[-2]

    def isSilence(self):
        return self._words == "";

    def isShouting(self):
        if (self._words.isupper() or self._lastSentence.isupper()):
            return True;
        return False;
    
    def isQuestion(self):
        if (self.isShouting()):
	    return False;
        if (self._punctuation.endswith("?")):
	    return True;
	return False;
