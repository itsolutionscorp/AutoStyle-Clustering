WHATEVER = "Whatever."
FINE = "Fine. Be that way."
CHILLOUT = "Woah, chill out!"
SURE = "Sure."

class Bob:
    def is_yell(self, sentence):
        letters = 0
        caps = 0
        for char in sentence:
            charc = ord(char)
            if charc >= 64 and charc <= 90:
                letters += 1
                caps += 1
                continue
            if charc >= 97 and charc <= 122:
                letters += 1
                continue
        if letters == 0:
            return False
        if caps/letters >= 0.8:
            return True
        return False
    
    def hey(self, sentence):
        if sentence is None:
            return FINE
        sentence = sentence.strip()
        if len(sentence) == 0:
            return FINE
        if self.is_yell(sentence):
            return CHILLOUT
        lastchar = sentence[-1]
        if lastchar == "?":
            return SURE
        return WHATEVER
