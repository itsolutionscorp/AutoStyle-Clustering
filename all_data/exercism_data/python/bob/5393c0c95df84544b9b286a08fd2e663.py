#
# Skeleton file for the Python "Bob" exercise.
#
# Flowchart:
# Check for shouting: Yes -> u'Whoa, chill out!'
# Check for question: Yes -> u'Sure.'
# Check for empty: Yes => u'Fine. Be that way!'
# u'Whatever.'

def is_a_shout(text):
    from re import sub, U
    letters_only = sub(r'[0-9_\W]', '', text, flags=U)
    return letters_only.isupper()

def is_a_question(text):
    from re import search
    return search(r'\?\s*$', text)

def is_empty(text):
    return len(text.strip()) == 0

def hey(what):
    text = unicode(what)
    if is_a_shout(text):
        return u'Whoa, chill out!'
    elif is_a_question(text):
        return u'Sure.'
    elif is_empty(text):
        return u'Fine. Be that way!'
    else:
        return u'Whatever.'
