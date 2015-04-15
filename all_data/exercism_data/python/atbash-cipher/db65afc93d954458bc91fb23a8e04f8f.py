plain = 'abcdefghijklmnopqrstuvwxyz'
cipher = 'zyxwvutsrqponmlkjihgfedcba'
numbers = '0123456789'


def decode(input_str):
    decoded = ""
    for letter in input_str:
        if letter in plain:
            decoded += cipher[plain.find(letter)]
        elif letter in numbers:
            decoded += letter
    return decoded


def encode(input_str):
    input_str = input_str.lower()
    encoded = ""
    for letter in input_str:
        if letter in plain:
            encoded += plain[cipher.find(letter)]
        elif letter in numbers:
            encoded += letter
    return encode_format(encoded)


def encode_format(input_str):
    output_str = ""
    for i in range(0, len(input_str)):
        if i > 0 and i % 5 == 0:
            output_str += (" " + input_str[i])
        else:
            output_str += input_str[i]
    return output_str
