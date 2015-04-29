def hey(what):
    say = 'Whatever.'
    if what.isspace() or not what:
        say = 'Fine. Be that way!'
    elif what.isupper():
        say = 'Whoa, chill out!'
    elif what.strip()[-1] == '?':
        say = 'Sure.'
    return say
