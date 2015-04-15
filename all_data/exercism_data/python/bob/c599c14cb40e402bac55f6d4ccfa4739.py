def hey(what):
    if not what.split():
        bob_say = "Fine. Be that way!"
    elif what.isupper():
        bob_say = "Whoa, chill out!"
    elif what.endswith('?'):
        bob_say = "Sure."
    else:
        bob_say = "Whatever."
    return bob_say
