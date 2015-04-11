def hey(input_str):
    if not input_str or all([c.isspace() for c in input_str]):
        return 'Fine. Be that way!'
    elif input_str == input_str.upper() and any([c.isalpha() for c in input_str]):
        return 'Whoa, chill out!'
    elif input_str[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
