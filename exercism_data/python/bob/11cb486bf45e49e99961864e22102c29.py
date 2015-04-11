def hey(saying):
    if len(saying.rstrip()) == 0:
        return 'Fine. Be that way!'
    elif (saying.upper() == saying) & (saying.capitalize() != saying):
        return 'Whoa, chill out!'
    elif saying.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
