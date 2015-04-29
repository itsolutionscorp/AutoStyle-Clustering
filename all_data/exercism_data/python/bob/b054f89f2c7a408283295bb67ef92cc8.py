def hey(text):
    text = text.strip()
    if len(text) == 0:
        return 'Fine. Be that way!'
    text = text.translate({ord(i) :None for i in ',0123456789'})
    text = text.strip()
    if len(text) == 0:
        return 'Whatever.'
    if text.upper() == text:
        return 'Whoa, chill out!'
    if '?' == text[-1]:
        return 'Sure.'
    return 'Whatever.'
