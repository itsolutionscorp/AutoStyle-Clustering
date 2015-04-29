#
# Skeleton file for the Python "Bob" exercise.
#
'''
The order of the cases is important in this function:
we first need to check for shouts/uppercase to get that
out of the way. No matter what the shouting phrases end
with or what they contain, they are still shouts. Then 
we can analyze everything else
'''
def hey(what):
    # Bob will consider ALL CAPS (uppercase) as shouting at him
    # no matter what character the phrase ends with
    if what.isupper():
        return 'Whoa, chill out!'
    # But if the phrase is lowercase and ends with '?' it will
    # consider it a question
    elif what.endswith('?'):
        return 'Sure.'
    # Bob doesn't like to be bothered for nothing
    elif not what or what.isspace():
        return 'Fine. Be that way!'
    # And anything else is a blur to him
    else:
        return 'Whatever.'
