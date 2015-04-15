def hey(what):
    what = what.strip()

    if ignored(what):
        return 'Fine. Be that way!'
    elif yelled_at(what):
        return 'Whoa, chill out!'
    elif questioned(what):
        return 'Sure.'
    else:
        return 'Whatever.'


def ignored(what):
    return not what


def yelled_at(what):
    return what.isupper()


def questioned(what):
    return what.endswith("?")
