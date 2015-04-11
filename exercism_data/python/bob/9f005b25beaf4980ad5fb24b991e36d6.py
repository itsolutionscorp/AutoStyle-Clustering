
def hey(message):
    text = message.strip()
    result = 'Whatever.'
    if text:
        is_quest = text.endswith('?')
        is_upper = text.upper() == text
        is_alpha = not (text.upper() == text.lower())
        if is_upper and is_alpha:
            result = 'Whoa, chill out!'
        elif is_quest:
            result = 'Sure.'
    else:
        result = 'Fine. Be that way!'
    return result
