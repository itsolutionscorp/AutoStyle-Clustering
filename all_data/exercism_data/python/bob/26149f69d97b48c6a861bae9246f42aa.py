import re

class Bob:
    def hey(self, statement):
        return self.DialogicExchange(statement).response()

    class DialogicExchange:
        YELL = re.compile('^[^a-z]+$')

        def __init__(self, statement):
            if statement is None:
                self.statement = ''
            else:
                self.statement = statement.strip()

        def response(self):
            if self.__yells():
                return 'Woah, chill out!'
            elif self.__asks_a_question():
                return 'Sure.'
            elif self.__says_nothing():
                return 'Fine. Be that way!'
            else:
                return 'Whatever.'

        def __yells(self):
            return self.YELL.match(self.statement)

        def __asks_a_question(self):
            return self.statement.endswith('?')

        def __says_nothing(self):
            return not self.statement
