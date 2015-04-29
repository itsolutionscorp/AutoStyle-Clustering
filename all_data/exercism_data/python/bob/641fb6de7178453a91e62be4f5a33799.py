#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    if what.strip() == '':
        # Strips the code of any whitespace and tests to see if it is empty of content
        return 'Fine. Be that way!'
    elif what.isupper():
        # Tests if code is Exclamatory
        return 'Whoa, chill out!'
    elif what[(len(what)-1)] == '?':
        # Tests for a Question mark at the end of the sentence
        return 'Sure.'
    else:
        return 'Whatever.'
