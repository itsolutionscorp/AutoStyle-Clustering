#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    answer = 'Whatever.'
    if yell(what):
        answer = 'Whoa, chill out!'
    elif question(what):
        answer = 'Sure.'
    elif nonsense(what):
        answer = 'Fine. Be that way!'
    return answer

def question(what):
    return what.strip().endswith('?')

def yell(what):
    return what.strip().isupper()

def nonsense(what):
    return not what.strip()
