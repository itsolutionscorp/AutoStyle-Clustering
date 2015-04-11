def hey(x):
    if x.isupper():
        return 'Woah, chill out!'
    if x.endswith('?'):
        return 'Sure.'
    if not x.strip():
        return 'Fine. Be that way!'
    return 'Whatever.'
