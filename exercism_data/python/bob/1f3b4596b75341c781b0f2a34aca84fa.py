#
# Skeleton file for the Python "Bob" exercise.
# 
def hey(what):
    # Test if string is empty, has space or tab.
    if not what or what.isspace():
        return 'Fine. Be that way!'

    # Test if string is not empty, has ? mark at the end not uppercase.
    elif not what or what[-1] == '?' and not what.isupper():
        return 'Sure.'
    
    # Test if string all uppercase or has ! mark at the end and it's uppercase.
    elif what[-1] == '!' and what.isupper() or what.isupper():
        return 'Whoa, chill out!'

    else:
        return 'Whatever.'
