def hey(what):
    """
    Responds with
    'Sure.' if you ask him a question.
    'Whoa, chill out!' if you yell at him.
    'Fine. Be that way!' if you address him without actually saying anything.
    'Whatever.' to anything else.
    """
    # remove trailing white space
    what = what.rstrip()

    if what == '':
        # empty string
        response = 'Fine. Be that way!'
    elif what.isupper():
        # yelling ALL CAPS
        response = 'Whoa, chill out!'
    elif what.endswith('?'):
        # question mark at end
        response = 'Sure.'
    else:
        # anything else
        response = 'Whatever.'

    return response
