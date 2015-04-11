#
# Skeleton file for the Python "Bob" exercise.
# -*- encoding: utf-8 -*-
class SentenceInterpreter:
    
    def __init__(self,sentence = None):
        self.sentence = str(sentence.strip())
            
    
    def is_silence(self):
        if self.sentence is '':
            return True
        else:
            return False
    
    def is_question(self):
        return self.sentence.endswith('?') 
    
    def is_shouting(self):
        if self.sentence =='':
            return False
        else:
            return self.sentence == self.sentence.upper()
    
class bob:
        
    def hey(self,at):
        self.at = at
        intrptr = SentenceInterpreter(sentence = at)
        if intrptr.is_shouting():
            return "Woah, chill out!"
        elif intrptr.is_question():
            return "Sure."
        elif intrptr.is_silence():
            return "Fine. Be that way!"
        return "Whatever."
