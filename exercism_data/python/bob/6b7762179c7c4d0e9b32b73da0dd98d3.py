class Bob():
    def hey(self, said):

        containsLetters = said.upper() != said.lower()

        if containsLetters and said == said.upper():
            return "Woah, chill out!"
        
        if said.endswith("?"):
            return "Sure."

        if said.strip() == "":
            return "Fine. Be that way!"
        
        return "Whatever."
        
