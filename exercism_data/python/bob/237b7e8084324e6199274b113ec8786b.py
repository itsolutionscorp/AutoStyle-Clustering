def hey(phrase):
    # Strip the whitespace here stop it from distorting a phrase
    # (ex: whitespace after a '?').
    phrase = phrase.strip()

    # Are we yelling at Bob?
    if phrase.isupper():
        return 'Whoa, chill out!'
    # Are we asking Bob a question?
    elif phrase.endswith('?'):
        return 'Sure.'
    # Are we saying nothing to Bob?
    elif not phrase:
        return 'Fine. Be that way!'
    # Or are we saying literally anything else to Bob.
    else:
        return 'Whatever.'
