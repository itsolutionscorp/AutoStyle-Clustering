def hey(phrase):
    phrase = phrase.strip()
    if not phrase:
        return 'Fine. Be that way!'
    elif any(c.isalpha() for c in phrase) and phrase.upper() == phrase:
        return 'Whoa, chill out!'
    elif phrase.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
