def hey(what):
    say = what.strip()
    if say.isupper():
        return 'Whoa, chill out!'
    elif '?' == say[-1:]:
        return 'Sure.'
    elif not say.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
