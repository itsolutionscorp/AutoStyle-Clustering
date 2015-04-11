def hey(text):

    if text.isupper():
        return 'Whoa, chill out!'

    if text.endswith("?"):
        return 'Sure.'

    if len(text) == 0 or text.isspace():
        return 'Fine. Be that way!'

    return 'Whatever.'
