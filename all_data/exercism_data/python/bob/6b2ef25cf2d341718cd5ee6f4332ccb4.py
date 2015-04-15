def hey(what):
    
    jackal = what.strip()
    
    first = jackal.isupper()
    second = jackal.endswith('?')
    third = not jackal

    
    if first:
        return 'Whoa, chill out!'
    
    elif second:
        return 'Sure.'

    elif third:
        return 'Fine. Be that way!'

    else:
        return 'Whatever.'
