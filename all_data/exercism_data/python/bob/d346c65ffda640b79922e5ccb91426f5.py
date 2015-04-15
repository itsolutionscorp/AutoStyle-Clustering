#
#   
#
def hey(what):
    # All Upper case 
    if what.isupper():
        return 'Whoa, chill out!'
    # 0 length or all spaces including tabs
    elif not what or what.isspace():
        return 'Fine. Be that way!'
    # Ends in a ?  
    elif what and what[-1] in ' ?':
        return 'Sure.'

    # Everything else
    return 'Whatever.'
