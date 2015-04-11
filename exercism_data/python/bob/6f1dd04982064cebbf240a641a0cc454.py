from itertools import ifilter 

def hey(what):
    teenager = [
        (lambda w: len(w) == 0 or w.isspace(), 'Fine. Be that way!'),
        (lambda w: w.isupper(),                'Whoa, chill out!'),
        (lambda w: w.endswith("?"),            'Sure.'),
        (lambda w: True,                       'Whatever.'),
    ]

    fn, response = next(ifilter(lambda t: t[0](what), teenager))
    return response
