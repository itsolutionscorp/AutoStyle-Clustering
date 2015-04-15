def hey(what):
    if not what.strip():
        return 'Fine. Be that way!'
    elif what.isupper():
        # Check if input is shout
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        # Or if sentence ends with a ?
        return 'Sure.'
    else:
        # Otherwise
        return 'Whatever.'
