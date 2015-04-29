#
# Skeleton file for the Python "Bob" exercise.
#

responses = {

'question' : 'Sure.',
'yell' : 'Whoa, chill out!',
'blank' : 'Fine. Be that way!',
'default' : 'Whatever.'

}

def hey(what):

    what = what.rstrip() # strip whitespace off the end for easier testing

    if not what or what.isspace():
        return responses['blank']

    elif what[-1] == "?" and not what.isupper():
        return responses['question']

    elif what.isupper():
        return responses['yell']

    else:
        return responses['default']
