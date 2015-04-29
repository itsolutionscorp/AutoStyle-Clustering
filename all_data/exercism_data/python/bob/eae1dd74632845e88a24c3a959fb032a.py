class Bob:

    def isShouting(self,s):
        return s == s.upper() and s != s.lower()

    def isQuestion(self,s):
        return s.endswith("?") 

    def isSilent(self,s):
        return s.strip() == ""

    def hey(self,s):
        if self.isSilent(s):
            return "Fine. Be that way!"
        elif self.isShouting(s):
            return "Woah, chill out!"
        elif self.isQuestion(s):
            return "Sure."
        else:
            return "Whatever."
