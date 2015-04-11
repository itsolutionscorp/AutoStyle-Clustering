def hey(input):
    if ''.join(input.split()) == '':
        return 'Fine. Be that way!'
    elif input == input.upper() and input != input.lower():
        return 'Whoa, chill out!'
    elif input[len(input) - 1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
