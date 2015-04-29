def hey(what):
    teenager = [
        (lambda: len(what) == 0 or what.isspace(), 'Fine. Be that way!'),
        (lambda: what.isupper(),                   'Whoa, chill out!'),
        (lambda: what.endswith("?"),               'Sure.'),
        (lambda: True,                             'Whatever.'),
    ]

    for should_respond, response in teenager:
        if should_respond():
            return response
