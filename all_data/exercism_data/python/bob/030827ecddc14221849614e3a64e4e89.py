def hey(input):
    if input.isupper():
        return 'Woah, chill out!'
    elif input.endswith('?'):
        return 'Sure.'
    elif len(input.strip()) == 0:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
