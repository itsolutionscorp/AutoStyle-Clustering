from string import ascii_lowercase

letters = ascii_lowercase
reverse = letters[::-1]

def decode(text):
    code = list(text.lower())
    result = ''

    for c in code:
        if c.isalpha():
            result += reverse[letters.index(c)]
        if c.isdigit():
            result += c

    return result

def encode(text):
    code = list(text.lower())
    result = ''

    i = 0
    for c in code:
        if c.isalnum():
            if i and not i % 5:
                result += ' '
            i += 1
            if c.isalpha():
                result += reverse[letters.index(c)]
            else:
                result += c

    return result
