import string


# Create a table to replace all whitespace, punctuation characters and digits
# with a space.
table = str.maketrans(string.whitespace + string.punctuation + string.digits,
                      ' ' * 48)
# This function removes all whitespace, punctuation and digits from the string
translate = lambda s: s.translate(table).replace(' ', '')

# Each answer is associated with a function that tests the string
tests = [
    (
        lambda i: not i.strip(),
        'Fine. Be that way!'
    ),
    (
        lambda i: (lambda k: k and k == k.upper())(translate(i)),
        'Woah, chill out!'
    ),
    (
        lambda i: i.endswith('?'),
        'Sure.'
    ),
]


def hey(sentence):
    for test, answer in tests:
        if test(sentence):  # if the test is successful, then return the
            return answer   # associated answer
    return 'Whatever.'
