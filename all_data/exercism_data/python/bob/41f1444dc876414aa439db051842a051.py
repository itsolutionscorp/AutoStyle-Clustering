# -*- coding: utf-8 -*-


def hey(request):
    request = request.strip()

    if not request:
        return 'Fine. Be that way!'
    if request.isupper():
    	return 'Whoa, chill out!'
    if request.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
