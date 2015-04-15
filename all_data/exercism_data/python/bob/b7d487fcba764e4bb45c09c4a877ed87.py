def hey(what):
    all_upper = what.isupper()
    if len(what.strip()) == 0:
        return 'Fine. Be that way!'    
    if what[-1] == '?' and not all_upper:
        return 'Sure.'
    elif what.__contains__('!') and all_upper:
        return 'Whoa, chill out!'
    elif all_upper:
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
