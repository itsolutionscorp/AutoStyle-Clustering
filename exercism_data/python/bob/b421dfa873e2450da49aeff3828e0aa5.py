# Bob program to pass bob_test.py
def hey(request):
    # remove all whitespace first
    # this will also remove accidental whitespace
    # if whitespace was after a question mark, etc
    request = request.strip()

    # if nothing is said
    if not request:
        return u'Fine. Be that way!'

    # if yelling, uppercase questions are yelling
    elif request.isupper():
        return u'Whoa, chill out!'

    # Question response
    elif request[-1] == '?':
        return u'Sure.'

    # catch all response
    else:
        return u'Whatever.'
