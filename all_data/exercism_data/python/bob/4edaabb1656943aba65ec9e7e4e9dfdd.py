
def hey(statement):

    if statement.isupper():
        return 'Whoa, chill out!'

    elif len(statement) > 0 and statement[-1] == '?':
        return 'Sure.'

    elif statement.replace(' ', '').replace('\t', '') == '':
        return 'Fine. Be that way!'

    else:
        return 'Whatever.'
