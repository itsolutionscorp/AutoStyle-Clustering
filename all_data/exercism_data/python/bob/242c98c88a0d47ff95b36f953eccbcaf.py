class Statement():

    def __init__(self, statement):
        self.statement = statement

    def is_question(self):
        '''Statement.is_question()

        If the last non-whitespace character is a question mark, it's a question
        '''

        statement_without_whitespace = self.statement.rstrip()

        if not statement_without_whitespace: return False
        last_char = statement_without_whitespace[-1] 
        if last_char == '?':
            return True

    def is_yelling(self):
        '''Statement.is_yelling()

        If all of the letters are upper case, they're yelling
        '''

        if self.statement.isupper():
            return True

    def says_nothing(self):
        '''Statement.says_nothing()
        
        If it contains nothing or just whitespace, it says nothing
        '''

        statement_without_whitespace = self.statement.strip()
        if statement_without_whitespace == '':
            return True



def hey(statement):

    says = Statement( statement )

    if says.is_yelling(): return 'Whoa, chill out!'

    if says.is_question(): return 'Sure.'

    if says.says_nothing(): return 'Fine. Be that way!'

    return 'Whatever.'
