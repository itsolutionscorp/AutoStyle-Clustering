def hey(what):
    def has_letters():
        for char in what:
            if char.isalpha():
                return True
        return False

    def is_shouting():
        for char in what:
            if not char.upper() == char and char.isalpha():
                return False
        return True

    if not what.split():
        bob_say = "Fine. Be that way!"
    elif has_letters() and is_shouting():
        bob_say = "Whoa, chill out!"
    elif what.endswith('?'):
        bob_say = "Sure."
    else:
        bob_say = "Whatever."
    return bob_say
