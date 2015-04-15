import re

class Bob(object):
    def is_question(self, prompt):
        if self.is_shout(prompt):
            return False
        else:
            return prompt.endswith("?")
    
    def is_blank(self, prompt):
        return not prompt.strip()
    
    def is_shout(self, prompt):
        #return (prompt == prompt.upper())
        contains_lowercase = len(re.findall("[a-z]", prompt)) > 0
        contains_uppercase = len(re.findall("[A-Z]", prompt)) > 0
        return contains_uppercase and not contains_lowercase
    
    
    def hey(self, prompt):
        if self.is_blank(prompt):
            return "Fine. Be that way!"
        elif self.is_question(prompt):
            return "Sure."
        elif self.is_shout(prompt):
            return "Whoa, chill out!"
        else:
            return "Whatever."
