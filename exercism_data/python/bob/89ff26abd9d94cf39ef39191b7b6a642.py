#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    if what.strip().endswith('?') and not what.isupper():
        answer = 'Sure.'
    elif what.isupper():
        answer = 'Whoa, chill out!'
    elif not what.strip():
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'

    return answer

"""
if __name__ == '__main__':
    answer = hey('hello')
    print(answer)
"""
