def hey(inp):
    potential_replies = (
        # Ordering matters!
        (lambda x: x.isspace() or x == '', 'Fine. Be that way!'),
        (lambda x: x.isupper(), 'Whoa, chill out!'),
        (lambda x: x.endswith('?'), 'Sure.'),
    )

    for predicate, reply in potential_replies:
        if predicate(inp):
            return reply
    return 'Whatever.'
