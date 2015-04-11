def hey(text):

    if text == None or text.strip() == '':
        return 'Fine. Be that way!'
    if text.isupper():
        return 'Whoa, chill out!'
    if text.strip().endswith("?"):
        return 'Sure.'
    if text.isnumeric():
        return 'Whatever.'

    return 'Whatever.'
