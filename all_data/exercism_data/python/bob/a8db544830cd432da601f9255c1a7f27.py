import re
import unicodedata

class Bob:

    def hey (self, statement):
        # First thing I'm going to do is clean the string
        # of things that don't affect the results
        shortStatement = self.cleanStatement(statement)
        punctuation = statement[-1:]
        if not shortStatement:
            return 'Fine. Be that way!'
        elif not shortStatement.isupper() and punctuation != '?':
            return 'Whatever.'
        elif not shortStatement.isupper() and punctuation == '?':
            return 'Sure.'
        elif shortStatement.isupper():
            return 'Woah, chill out!'

    def cleanStatement (self, statement):
        newStatement = statement
        if isinstance(newStatement, unicode):
            newStatement = unicodedata.normalize('NFKD', newStatement).encode('ascii', 'ignore')

        cleaned = re.sub('[^a-zA-Z\?\,\n]', '', newStatement);
        return cleaned[-4:]
