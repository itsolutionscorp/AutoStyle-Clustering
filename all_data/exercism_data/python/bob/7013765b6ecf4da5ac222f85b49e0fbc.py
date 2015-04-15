class Bob(object):
    def hey(self, statement):
        s = Statement(statement)
        
        if s.silence():
            return "Fine. Be that way."
        
        if s.shout():
            return "Woah, chill out!"
        
        if s.question():
            return "Sure."
        
        return "Whatever."


class Statement(object):
    def __init__(self, statement):
        self.statement = statement
    
    def silence(self):
        return not self.statement.strip()
        
    def shout(self):
        return self.statement.isupper()
    
    def question(self):
        return self.statement.endswith('?')
