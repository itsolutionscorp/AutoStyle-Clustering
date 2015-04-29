def hey(statement):
    #being shouted at
    if statement.lstrip() == '' or statement.rstrip() == '':
        return 'Fine. Be that way!'
    elif (statement.isupper()):
        return 'Whoa, chill out!'
    elif (statement[-1] == '?'):
        return 'Sure.'
    else:
        return 'Whatever.'
