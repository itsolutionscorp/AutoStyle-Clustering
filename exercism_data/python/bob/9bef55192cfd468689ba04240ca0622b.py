# Bob program to pass bob_test.py
def hey(request):

    # Remove whitespace from request then check if it
    # is still something
    if not request.strip():
        return u'Fine. Be that way!'

    # Yelling response
    # Precedes questions, because all uppercase question
    # is actually yelling
    elif request.isupper():
        return u'Whoa, chill out!'

    # Question response
    # The [-1] tests for the last character of string
    elif request[-1] == '?':
        return u'Sure.'

    # catch all response
    else:
        return u'Whatever.'
