def hey(line):
    if line.isupper():
        return 'Whoa, chill out!'
    elif not line or line.isspace():
        return 'Fine. Be that way!'
    elif line.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
