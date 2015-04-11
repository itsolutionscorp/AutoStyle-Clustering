#
# Skeleton file for the Python "Bob" exercise.
#

NON_IMPERATIVE_WORDS_FRAGMENTS = ['\'re', '\'s', '\'m']

def contains_stupid_unicode_char(str):
    return u'\xe4' in str

def is_imperative(sentence):
    for entry in NON_IMPERATIVE_WORDS_FRAGMENTS:
        if entry in sentence.lower():
            return False
    return True


def hey(what):
    what = what.strip()
    answer = 'Whatever.' 
    if not what:
        answer = 'Fine. Be that way!'
    elif (what.endswith('!') or all([word.isupper() for word in what.split()])) \
         and is_imperative(what) and not contains_stupid_unicode_char(what):
        answer = 'Whoa, chill out!'
    elif what.endswith('?'):
        answer = 'Sure.'
    return answer
