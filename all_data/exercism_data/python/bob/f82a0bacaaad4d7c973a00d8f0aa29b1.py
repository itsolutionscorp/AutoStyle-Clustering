class Bob():
    def hey(self, said):

        if said.isupper():
            return "Woah, chill out!"
        
        if said.endswith("?"):
            return "Sure."

        if not said or said.isspace():
            return "Fine. Be that way!"
        
        return "Whatever."
        
