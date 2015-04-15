def hey(input):
    if not input.strip():
        return 'Fine. Be that way!'
    elif input.isupper():
        return 'Whoa, chill out!'
    elif input[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
