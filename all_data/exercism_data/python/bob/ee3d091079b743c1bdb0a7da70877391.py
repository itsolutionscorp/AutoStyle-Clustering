#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):

    responses = {
    'silence': 'Fine. Be that way!',
    'yell': 'Whoa, chill out!',
    'question': 'Sure.',
    'generic': 'Whatever.'
    }

    if what.isspace() or what == '':
        return responses['silence']
    elif what.isupper():
        return responses['yell']
    else:
        for c in reversed(range(len(what))):
            if what[c] == '?':
                return responses['question']
            elif what[c] != ' ':
                break
    return responses['generic']
