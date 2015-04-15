def hey(thing):
    "Respond like a teenager."
    thing = thing.strip()
    if not thing:
        return 'Fine. Be that way!'
    elif thing.isupper():
        return 'Whoa, chill out!'
    elif thing.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
