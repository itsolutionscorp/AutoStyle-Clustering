def hey(phrase):
    # Strip the whitespace here to stop it from distorting a phrase
    # (ex: whitespace after a '?').
    phrase = phrase.strip()

    # Are we saying nothing to Bob?
    if not phrase:
        return 'Fine. Be that way!'
    # Are we yelling at Bob?
    elif phrase.isupper():
        return 'Whoa, chill out!'
    # Are we asking Bob a question?
    elif phrase.endswith('?'):
        return 'Sure.'
    # Or are we saying literally anything else to Bob.
    else:
        return 'Whatever.'
