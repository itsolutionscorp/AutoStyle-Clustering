class Bob():
    def hey( x, string ):
        if( string.isupper() ):
            return 'Woah, chill out!'
        elif( string.endswith('?') ):
            return 'Sure.'
        elif( string == '' ):
            return 'Fine. Be that way!'
        elif( string.isspace() ):
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
