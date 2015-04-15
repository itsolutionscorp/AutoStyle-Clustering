class Statement():

    def __init__(self, statement):
        # Strip the whitespace, it isn't relevant
        self.statement = statement.strip()

    def is_question(self):
        '''Statement.is_question()

        If the last character is a question mark, it's a question
        '''

        last_char = self.statement[-1] 
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

        if self.statement == '':
            return True



def hey(statement):

    says = Statement( statement )

    if says.says_nothing(): return 'Fine. Be that way!'

    if says.is_yelling(): return 'Whoa, chill out!'

    if says.is_question(): return 'Sure.'

    return 'Whatever.'
