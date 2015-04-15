import re
#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.strip() == '':
        return 'Fine. Be that way!'

    # Find all uppercase and lowercase words for later determinination if bob is being yelled at
    unicode_literals = re.compile("[^\W\d_ ]+", re.UNICODE)
    words_unicode = unicode_literals.findall(what)

    # Check if how many caps words exist
    uppercase, lowercase = [], []
    for word in words_unicode:
        if word.isalpha() and word.isupper():
            uppercase.append(word)
        else:
            lowercase.append(word)

    # Bob is being yelled at if the speaker uses more caps than normal words
    if len(uppercase) > len(lowercase):
        return 'Whoa, chill out!'
    if what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
