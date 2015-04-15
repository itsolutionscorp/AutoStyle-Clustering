#
# Skeleton file for the Python "Bob" exercise.
#

_conversation_check_response = [
    (lambda x: x.endswith('?'), 'Sure.'),
    (lambda x: x.isupper(), 'Whoa, chill out!'),
    (lambda x: not x, 'Fine. Be that way!'),
]


def hey(what):

    answer = 'Whatever.'
    for conversation_check, response in _conversation_check_response:
        if conversation_check(what.strip()):
            answer = response

    return answer
