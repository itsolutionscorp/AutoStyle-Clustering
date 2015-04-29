import re

class Bob( object ):
    def __init__( self ):
        re.IGNORECASE = False # turn on case-sensitive matching

    def hey(  self, str ):
        if re.match( '^\s*$', str ): # only whitespace
            return 'Fine. Be that way!'

        if re.match( u'^(?=(.*[A-Z\xdc\xc4\xd6]))[^a-z\xc3\xe4\xf6\xfc]*$', str ): # string is all-caps
            return 'Woah, chill out!'

        if str[-1:] == '?': # last character = ? -> question
            return 'Sure.'

        return 'Whatever.'
