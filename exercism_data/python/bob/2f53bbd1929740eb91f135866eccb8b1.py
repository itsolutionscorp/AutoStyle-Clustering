"""hey Bob"""


def hey(talk):
    if not talk or talk.strip() == '':
        return "Fine. Be that way!"
    if talk[-1] == '?':
        return "Sure."
    if talk[-1] == '!':
        return "Whoa, chill out!"
    if len(talk.split()) == 1:
        return "Whatever."
    if talk.split()[1].isupper():
        return "Whoa, chill out!"
    else:
        return "Whatever."
