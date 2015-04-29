def hey(what):
    what_trimmed = what.strip()
    if not what_trimmed:
        return u'Fine. Be that way!'
    what_words = filter(lambda character: character.isalpha(), what_trimmed)
    if what_words.isupper():
        return u'Whoa, chill out!'
    last_character = what_trimmed[-1]
    if last_character == '?':
        return u'Sure.'
    return u'Whatever.'
