def hey(what):
    statement = what.strip()

    if statement == '':
        return 'Fine. Be that way!'

    if statement.isupper():
        return 'Whoa, chill out!'

    if statement[-1] == '?':
        return 'Sure.'
    
    return 'Whatever.'
