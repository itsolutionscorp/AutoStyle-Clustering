"""Bob's exercice in Python on exercism.io."""

def hey(sentence):
    """Return bob's answer from the sentence he was told."""
    sentence = sentence.strip()
    if len(sentence) == 0:
        return 'Fine. Be that way!'
    elif (sentence.upper() == sentence and
            len([char for char in sentence if char.isalpha()]) > 0):
        return 'Whoa, chill out!'
    elif sentence[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
