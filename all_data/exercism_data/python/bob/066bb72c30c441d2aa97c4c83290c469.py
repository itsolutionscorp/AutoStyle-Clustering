class Bob:
    def hey(self, input):
        if (input.isupper() == False) and (input.endswith("?")): 
            return "Sure."
        elif (input == "") or (input.isspace()):
            return "Fine. Be that way!"
        elif input.isupper() == True:
            return "Woah, chill out!"
        elif (input.isupper() == True) and (input.endswith("?")):
            return "Woah, chill out!"
        else:
            return "Whatever."
