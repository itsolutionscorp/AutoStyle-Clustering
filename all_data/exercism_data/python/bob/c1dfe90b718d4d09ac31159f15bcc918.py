import re

def hey(words):
    text = TextQualifier(words);
    if text.is_silence():
        return "Fine. Be that way!"
    if text.is_shouting():
        return "Woah, chill out!"
    if text.is_question():
        return "Sure."
    return "Whatever."

class TextQualifier(object):
    def __init__(self, words):
        self._words = words
        self._strip();
        self._add_punctuation_if_absent();
        self._parse();

    def _strip(self):
        self._words = self._words.strip();

    def _add_punctuation_if_absent(self):
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

    def is_silence(self):
        return self._words == "";

    def is_shouting(self):
        if (self._words.isupper() or self._lastSentence.isupper()):
            return True
        return False
    
    def is_question(self):
        if (self.is_shouting()):
            return False
        if (self._punctuation.endswith("?")):
            return True
        return False
