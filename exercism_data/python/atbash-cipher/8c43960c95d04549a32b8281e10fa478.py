def encode(input):
    return group(cipher(lower_alnums(input)), 5)

def decode(input):
    return cipher(lower_alnums(input))

def lower_alnums(input):
    return ''.join([x.lower() for x in input if x.isalnum()])

def cipher(input):
    alphabets = 'abcdefghijklmnopqrstuvwxyz'
    reverse = alphabets[::-1]
    return input.translate(str.maketrans(alphabets, reverse))

def group(input, size):
    result = ''
    for i in range(0, len(input), size):
        result = result + ' ' + input[i:i+size]
    return result.strip()
