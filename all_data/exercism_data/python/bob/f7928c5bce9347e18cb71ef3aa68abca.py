def hey(input):
    # if only whitespace
    if input.strip() ==  '':
        return 'Fine. Be that way!'
    # else if all uppercase
    elif input.isupper():
        return 'Whoa, chill out!'
    # else if ends in a '?'
    elif input.endswith('?'):
        return 'Sure.'
    # otherwise
    else:
        return 'Whatever.'
