class Bob:
    
    def __init__(self):
        pass

    
    def hey(self, sentence):
        if sentence.isspace() or len(sentence) == 0:
            return "Fine. Be that way!"
        elif sentence.isupper():
            return "Woah, chill out!"
        elif sentence[-1] == "?":
            return "Sure."
        else:
            return "Whatever."
