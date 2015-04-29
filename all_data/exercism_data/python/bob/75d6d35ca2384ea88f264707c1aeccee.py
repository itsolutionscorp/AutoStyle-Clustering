def hey(statement):
    response = 'Whatever.'
    if len([x for x in statement if x.isalnum()]) == 0:
        response = 'Fine. Be that way!'
    elif any(x.isalpha() for x in statement) and statement.upper() == statement:
        response = 'Whoa, chill out!'
    elif statement[-1] == '?':
        response = 'Sure.'
    return response
