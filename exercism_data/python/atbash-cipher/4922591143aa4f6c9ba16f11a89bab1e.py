from string import ascii_lowercase

letters = ascii_lowercase
reverse = letters[::-1]

def translate(text):
    code = list(text.lower())
    result = ''

    for c in code:
        if c.isalpha():
            result += reverse[letters.index(c)]
        if c.isdigit():
            result += c

    return result

def decode(text):
    return translate(text)

def encode(text):
    code = translate(text)
    result = ''

    for i in range(0, len(code), 5):
        result += code[i:i+5] + ' '

    if result[-1] == ' ':
        result = result[:-1]

    return result
