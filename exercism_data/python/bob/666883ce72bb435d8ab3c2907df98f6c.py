def hey(str):

    if str.isupper():
        return 'Whoa, chill out!'

    elif str.endswith('?'):
        return 'Sure.'

    elif not str.strip():
        return 'Fine. Be that way!'

    else:
        return 'Whatever.'
