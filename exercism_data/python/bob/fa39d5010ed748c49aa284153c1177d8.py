
def hey(phrase):
    if _is_silent(phrase):
        return "Fine. Be that way!"
    elif _is_shouting(phrase):
        return "Whoa, chill out!"
    elif _is_question(phrase):
        return 'Sure.'
    else:
        return 'Whatever.'

def _is_silent(phrase):
    return len(phrase.strip()) == 0

def _is_question(phrase):
    return phrase.endswith('?')

def _is_shouting(phrase):
    return phrase.isupper()
