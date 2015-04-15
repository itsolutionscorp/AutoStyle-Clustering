'''
Bob, a lackadaisacal teenager, responds.
Say hey.  See what Bob says.
'''


def hey(what):
    "Return Bob's response to what."
    stripped = what.strip()
    if stripped == '':
        return 'Fine. Be that way!'

    if stripped.isupper():
        return 'Whoa, chill out!'

    if stripped[-1] == '?':
        return 'Sure.'

    return "Whatever."
