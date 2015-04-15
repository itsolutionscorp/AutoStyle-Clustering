#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    is_shouting = what.isupper()
    is_question = what.endswith('?')
    is_not_saying_anything = not what

    # Order is important. A shouted question == shout, and not a question.
    if is_shouting:
        return 'Whoa, chill out!'
    elif is_question:
        return 'Sure.'
    elif is_not_saying_anything:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
