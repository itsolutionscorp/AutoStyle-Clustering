def __init__():
    pass

def hey(what):
    if(what.isupper()):
        return 'Whoa, chill out!'

    if(what != '' and what[-1] == '?'):
        return 'Sure.'

    if(what.split() == []):
        return 'Fine. Be that way!'


    return 'Whatever.'
