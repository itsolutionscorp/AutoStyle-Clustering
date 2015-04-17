class Bob:
    def answer_to(self,statement):
        if statement.is_silence():
            return "Fine. Be that way!"
        elif statement.is_yelling():
            return "Woah, chill out!"
        elif statement.is_question():
            return "Sure."
        else:
            return "Whatever."

    def hey(self,saywhat):
        statement = Statement(saywhat)
        return self.answer_to(statement)

class Statement:
    def __init__(self,statement):
        self.statement=statement

    def is_yelling(self):
        "WHY ARE WE YELLING?"
        if self.statement is None:
            return False
        else:
            return self.statement.upper() == self.statement

    def is_silence(self):
        "Check for silence"
        # This three part if feels REALLY inelegant to me, but
        # just using return not self.statement.strip() blows up
        # on an actual empty string.
#        if not self.statement:
#            return True
#        elif not self.statement.strip():
#            return True
#        else:
#            return False
        if self.statement is None:
            return True
        else:
            return len(self.statement.strip()) == 0

    def is_question(self):
        if self.statement is None:
            return False
        else:
            return self.statement[len(self.statement) - 1] == '?'