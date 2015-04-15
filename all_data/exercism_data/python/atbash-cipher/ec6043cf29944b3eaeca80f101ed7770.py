
def remove_spaces(string):
    return ''.join(string.split())

def mirror_letters(string):
    result = []
    for c in string:
        if c.isalpha():
            result.append(chr(ord('z') - ord(c) + ord('a')))
        elif c.isnumeric():
            result.append(c)
    return ''.join(result)

def decode(cyphertext):
    cyphertext = remove_spaces(cyphertext)
    return mirror_letters(cyphertext)

def encode(cleartext):
    cleartext = remove_spaces(cleartext).lower()
    cyphertext = mirror_letters(cleartext)
    return ' '.join(cyphertext[i:i+5] for i in range(0, len(cyphertext), 5))


