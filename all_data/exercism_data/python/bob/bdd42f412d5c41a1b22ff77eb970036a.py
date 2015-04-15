"""Bob responds based on how  you talk to him."""


def hey(to_bob):
    """Return an appropriate response to input talk

    Saying nothing => Fine
    Yelling => Whoa
    Questions => Sure
    else => Whatever"""

    # remove spaces
    to_bob = to_bob.strip()

    if not len(to_bob):
        return "Fine. Be that way!"
    elif to_bob.isupper():
        return "Whoa, chill out!"
    elif to_bob.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
