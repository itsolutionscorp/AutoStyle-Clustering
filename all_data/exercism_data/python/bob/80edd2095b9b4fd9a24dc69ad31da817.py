import re
#
# Skeleton file for the Python "Bob" exercise.
#
RE_SENTENCE = re.compile('.*[a-zA-Z].*')
def hey(what):
    what = what.strip()
    all_caps = what.upper() == what
    is_question = what.endswith('?')
    is_sentence = RE_SENTENCE.match(what) is not None

    if is_sentence and all_caps:
        return 'Whoa, chill out!'
    elif is_question:
        return 'Sure.'
    elif not what:
        return 'Fine. Be that way!'
    return 'Whatever.'
