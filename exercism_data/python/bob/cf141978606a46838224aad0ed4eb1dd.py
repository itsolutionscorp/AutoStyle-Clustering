def hey(what):
    """Based on what was said to Bob, return verbal reply."""
    # Dict keys: what was said. Dict values: Bob's replies
    reply_to = {
        'nothing said': 'Fine. Be that way!',
        'yelling': 'Whoa, chill out!',
        'question': 'Sure.',
        'anything else': 'Whatever.',
    }
    said = what.strip()
    if not said:
        return reply_to['nothing said']
    # List matches conditions in order to ensure match result is consistent.
    for said_to_Bob, condition in [('yelling', said.isupper()),
                                   ('question', said[-1] == '?'), ]:
        if condition:
            return reply_to[said_to_Bob]
    else:
        return reply_to['anything else']
