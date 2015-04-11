# Each answer is associated with a function that tests the string
tests = [
    (
        lambda i: not i.strip(),
        'Fine. Be that way!'
    ),
    (
        lambda i: i.isupper(),
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
