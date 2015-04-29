#!/usr/bin/env python
class Bob():
    def hey(self, phrase=""):
        if phrase.isupper():
            answer = 'Woah, chill out!'
        elif phrase.endswith("?"):
            answer = "Sure."
        elif phrase.strip() == "":
            answer = "Fine. Be that way!"
        else:
            answer = "Whatever."
        return answer
    
