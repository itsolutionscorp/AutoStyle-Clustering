"""Bob's exercice in Python on exercism.io."""

def hey(sentence):
    """Return bob's answer from the sentence he was told."""
    if not sentence or sentence.isspace():
        return 'Fine. Be that way!'
    if sentence.isupper():
        return 'Whoa, chill out!'
    if sentence.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
