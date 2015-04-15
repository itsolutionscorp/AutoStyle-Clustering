import re

class Bob():
    def hey(self, statement):
        """ (obj, str) => str  
        It takes a Bob object and a str and returns a str depending
        on the statement.  If the statement is a question, returns 
        "Sure." If the statement is all caps, returns "Woah, chill out!"
        If the statement is only whitespace or empty, return 
        "Fine. Be that way!" Otherwise, returns "Whatever."
        """

        pattern = re.compile('[a-zA-Z]')
        blank_pattern = re.compile('\s')

        if pattern.search(statement) and statement == statement.upper():
            return("Woah, chill out!")
        elif statement[-1:] == '?':
            return("Sure.")
        elif blank_pattern.sub('', statement) == '':
            return('Fine. Be that way!')
        else:
            return("Whatever.")
