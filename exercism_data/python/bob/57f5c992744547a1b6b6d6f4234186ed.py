def hey(input):
    if not is_all_caps(input) and len(input) > 0 and '?' == input[-1]:
        return 'Sure.'
    elif is_empty_phrase(input):
        return 'Fine. Be that way!'
    elif is_all_caps(input):
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'

def is_empty_phrase(input):
    filtered = ''.join(input.split())
    return len(filtered) == 0

def is_all_caps(input):
    filtered = ''.join(input.split())
    return filtered.isupper()
