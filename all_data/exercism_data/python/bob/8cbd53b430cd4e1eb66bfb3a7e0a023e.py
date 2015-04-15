#
# Skeleton file for the Python "Bob" exercise.
#
""" simple exercice to learn exercism """


def analyse(sentence):
    ''' basic checks to find the type of sentence '''
    s = sentence.strip()
    # is the sentence the same in uppercase (but not in lowercase)?
    if s.isupper():
        return 'yell'
    # do we finish with a question mark?
    if s.endswith(u'?'):
        return 'question'
    # do we ask something?
    if not s:
        return 'nothing'
    # bailed out
    return 'error'


def lang(answer):
    ''' bob has a limited language, 4 cases only '''
    if answer == 'question':
        return u'Sure.'
    elif answer == 'yell':
        return u'Whoa, chill out!'
    elif answer == 'nothing':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'


def hey(what):
    ''' split prb in 2 phases: first analyse the sentence
        and then from this analysis return the expected sentence
    '''
    answer = analyse(what)
    return lang(answer)
