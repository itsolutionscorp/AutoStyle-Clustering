def hey(words):
    if words.strip()=='':
        return 'Fine. Be that way!'
    elif words==words.upper() and any((c.isalpha() for c in words)):
        return 'Whoa, chill out!'
    elif words[-1:]=='?':
        return 'Sure.'
    else:
        return 'Whatever.'
