import regex


def hey(text):
    if regex.match(u"^[^\p{Ll}]*$", text) and regex.search("[A-Z]+", text):
        return 'Whoa, chill out!'
    elif text.endswith("?"):
        return 'Sure.'
    elif len(text.strip()) == 0:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
