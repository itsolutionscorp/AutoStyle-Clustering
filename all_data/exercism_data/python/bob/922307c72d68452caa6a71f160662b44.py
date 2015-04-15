import re

class Bob:
    def hey(self, words):
        if not words or words == "" or re.search(" +$", words):
            return "Fine. Be that way!"
        
        if words.upper() == words and re.search("[A-Z]+", words):
            return "Woah, chill out!" 
        
        if re.search(".+\?$", words):
            return "Sure."

        return "Whatever."
