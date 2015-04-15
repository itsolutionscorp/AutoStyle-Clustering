import string

def hey(input):
    last_char = input[-1:]
    exclude = set('1234567890' + string.punctuation)

    if not input.strip():
        return 'Fine. Be that way!'

    input = ''.join(char for char in input if char not in exclude)

    if input.strip() and input == input.upper():
        return 'Whoa, chill out!'

    if last_char == '?':
        return 'Sure.'

    return 'Whatever.'
