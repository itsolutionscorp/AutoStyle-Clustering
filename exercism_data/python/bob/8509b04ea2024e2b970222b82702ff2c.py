class Bob:

    def hey(self,s):
        phrase = self.__Phrase(s)
        if phrase.is_silent():
            return "Fine. Be that way!"
        elif phrase.is_shouting():
            return "Woah, chill out!"
        elif phrase.is_question():
            return "Sure."
        else:
            return "Whatever."

    class __Phrase:

        def __init__(self,s):
            self.s = s

        def is_silent(self):   
            return not self.s.strip()

        def is_shouting(self): 
            return self.s.isupper()

        def is_question(self): 
            return self.s.endswith("?") and not self.is_shouting()
