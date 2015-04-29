def hey(statement):

    if not statement.strip(): # Check for blank input
        return 'Fine. Be that way!'

    elif statement.isupper(): # Check for all upper case
        return 'Whoa, chill out!'

    elif statement.endswith('?'): # Check for trailing question mark
        return 'Sure.'

    else: # Default response
        return 'Whatever.'
