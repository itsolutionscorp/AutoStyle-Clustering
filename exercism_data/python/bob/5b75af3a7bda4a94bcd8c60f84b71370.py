def hey(what):
    what = what.strip()

    upper_letters = [letter.isupper() for letter in what if letter.isalpha()]

    if not what:
        return 'Fine. Be that way!'
    elif upper_letters and all(upper_letters):
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
