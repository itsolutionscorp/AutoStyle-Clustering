# bob_v03.py
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
    

    newWhat = what.replace(',', '').replace(' ', '').replace('.', '')

    if newWhat.endswith('?') and not what.isupper():
        return bobResponse[0]
    elif newWhat.isupper():
        return bobResponse[2]
    elif not newWhat.strip():
        return bobResponse[3]
    else:
        return bobResponse[1]
