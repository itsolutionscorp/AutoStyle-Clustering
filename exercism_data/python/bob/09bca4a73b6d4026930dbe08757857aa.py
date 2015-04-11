def hey(statement):
    statement = statement.strip()
    if statement == '':
        return 'Fine. Be that way!'
    elif statement.isupper():
        return 'Whoa, chill out!'
    elif statement[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
