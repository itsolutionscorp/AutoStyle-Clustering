def hey(what):
    string, say = what.strip(), 'Whatever.'
    if string.isspace() or not string:
        say = 'Fine. Be that way!'
    elif string.isupper():
        say = 'Whoa, chill out!'
    elif string.endswith('?'):
        say = 'Sure.'
    return say
