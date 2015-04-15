def hey(input_str):
    cleaned = input_str.strip()
    if cleaned.isupper():
        return 'Whoa, chill out!'
    elif not cleaned:
        return 'Fine. Be that way!'
    elif cleaned[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
