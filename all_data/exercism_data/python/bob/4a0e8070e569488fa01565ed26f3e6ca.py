class Bob():
    @staticmethod
    def hey(sentence):
        if sentence == "": # Silence
            return "Fine. Be that way."
        if sentence.endswith("?"): # Question
            return "Sure."
        if sentence.isupper(): # Shouting
            return "Woah, chill out!"

        return "Whatever."
