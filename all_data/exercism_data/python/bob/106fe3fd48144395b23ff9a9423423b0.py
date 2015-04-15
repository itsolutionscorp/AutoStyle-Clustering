#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    question_response = 'Sure.'
    yelling_response = 'Whoa, chill out!'
    nothing_response = 'Fine. Be that way!'
    other_response = 'Whatever.'

    if what == '':
        return nothing_response
    if what.isupper():
        return yelling_response 
    if what[-1] == '?':
        return question_response 
    return other_response
