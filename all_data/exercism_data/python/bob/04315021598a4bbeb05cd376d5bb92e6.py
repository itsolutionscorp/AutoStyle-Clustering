# bob.py
def hey(what):
    """
        Bob Responses.
        Bob responds to ALL CAPS with a
        'Whoa, chill out!'
        Bob responds to questions of the non-yelling kind with a
        'Sure.'
        Bob responds to awkward silence with a
        'Fine. Be that way!'
        and everything else simply gets a
        'Whatever.'
    """

    bobResponse = ['Sure.', 'Whatever.',
                   'Whoa, chill out!',
                   'Fine. Be that way!']

    escCodes = ['\b', '\t', '\n', '\a', '\r'] # escape code set
    # remove unnecessary punctuation
    newWhat = what.replace(',', '').replace(' ', '').replace('.', '')
    # remove escape codes
    for code in escCodes:
        if code in newWhat:
            newWhat = newWhat.replace(code, '')
    
    if newWhat.endswith('?') and newWhat.isupper() is False:
        return bobResponse[0]
    elif newWhat.isupper() is True:
        return bobResponse[2]
    elif len(newWhat) == 0:
        return bobResponse[3]
    else:
        return bobResponse[1]


    
    # this may be missing all possible responses but I hope that
    # it covers most of them!
