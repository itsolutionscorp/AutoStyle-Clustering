def containsAlpha(text):
    return any(char.isalpha() for char in text)

def hey(text):

    if len(text) > 0 and text == text.upper() and containsAlpha(text):
        return 'Whoa, chill out!'

    if text.endswith("?"):
        return 'Sure.'

    if len(text) == 0 or text.isspace():
        return 'Fine. Be that way!'

    return 'Whatever.'
