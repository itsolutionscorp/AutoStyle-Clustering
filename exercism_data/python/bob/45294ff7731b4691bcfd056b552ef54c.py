def hey(input):
    if not input:
        return 'Fine. Be that way!'
    elif input.endswith("!"):
        return 'Woah, chill out!'
    elif input.endswith("?"):
        return 'Sure.'
    else:
        return 'Whatever.'
