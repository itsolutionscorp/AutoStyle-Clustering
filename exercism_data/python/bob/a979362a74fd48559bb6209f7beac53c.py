def hey(comment):
    """Talk to Bob.

    Args:
        comment: your statement or inquiry.
    Returns:
        Bob's response.

    """
    stripped = comment.strip()

    if stripped == '':
        return 'Fine. Be that way!'
    elif stripped.isupper():
        return 'Whoa, chill out!'
    elif stripped.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
