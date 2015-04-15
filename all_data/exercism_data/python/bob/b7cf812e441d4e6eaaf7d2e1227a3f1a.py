class Bob:
    def hey(self, statement):
        s = Statement(statement)
        
        if s.silence():
            return "Fine. Be that way."
        elif s.shout():
            return "Woah, chill out!"
        elif s.question():
            return "Sure."
        else:
            return "Whatever."

class Statement:
    def __init__(self, statement):
        self.statement = statement
    
    def silence(self):
        return not self.statement.strip()
        
    def shout(self):
        return self.statement == self.statement.upper()
    
    def question(self):
        return self.statement.endswith('?')
