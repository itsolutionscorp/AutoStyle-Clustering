class Bob:
    def hey(self, sentence):
        if is_silence(sentence):
            return "Fine. Be that way!"
        elif is_shouting(sentence):
            return "Woah, chill out!"
        elif is_question(sentence):
            return "Sure."
        else:
            return "Whatever."
            
def is_silence(sentence):
    return not sentence.strip()

def is_shouting(sentence):
    return sentence.isupper()

def is_question(sentence):
    return sentence.endswith("?")
