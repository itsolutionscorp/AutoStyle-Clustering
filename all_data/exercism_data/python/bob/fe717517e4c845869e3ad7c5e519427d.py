def hey(string):
    if len(string) == 0:
        return 'Fine. Be that way!'
    elif string[-1] == '?':
        return 'Sure.'
    elif string[-1] == '!':
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
