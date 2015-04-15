"""Bob responds based on how  you talk to him."""


def hey(talk):
    """Return an appropriate response to input talk

    Saying nothing => Fine
    Yelling => Whoa
    Questions => Sure
    else => Whatever"""
    lower_letters = [letter for letter in talk if letter.isalpha() and letter.islower()]
    upper_letters = [letter for letter in talk if letter.isalpha() and letter.isupper()]
    if not talk.strip():
        return "Fine. Be that way!"
    if upper_letters and not lower_letters:
        return "Whoa, chill out!"
    if talk[-1] == '?':
        return "Sure."
    else:
        return "Whatever."
