def hey(what):
    """
    Responds with
    'Sure.' if you ask him a question.
    'Whoa, chill out!' if you yell at him.
    'Fine. Be that way!' if you address him without actually saying anything.
    'Whatever.' to anything else.
    """

    # get rid of all trailing white space
    what = what.rstrip()

    if len(what) == 0:
        # empty string
        response = 'Fine. Be that way!'
    elif what == what.upper() and what.upper() != what.lower():
        # yelling ALL CAPS
        response = 'Whoa, chill out!'
    elif what[-1] == '?':
        # question mark at end
        response = 'Sure.'
    else:
        # anything else
        response = 'Whatever.'

    return response
