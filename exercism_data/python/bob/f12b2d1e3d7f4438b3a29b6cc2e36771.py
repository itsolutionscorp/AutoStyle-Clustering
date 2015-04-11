#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    what = what.rstrip()
    
    is_shouting = what.isupper()
    is_question = what.endswith('?')
    is_saying_nothing = what.isspace() or what == ''

    if is_shouting:
        return  'Whoa, chill out!'

    if is_question:
        return 'Sure.'

    if is_saying_nothing:
        return 'Fine. Be that way!'

    return 'Whatever.'
