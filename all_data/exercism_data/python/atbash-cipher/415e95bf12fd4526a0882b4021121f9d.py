import string
#a lot of refactoring to be done...

plain = string.ascii_lowercase
cipher = string.ascii_lowercase[::-1]

def decode(input_cipher):
    decoded_output = []
    for letter in input_cipher:
        cipher_letter_idx = cipher.find(letter)
        plain_letter = plain[cipher_letter_idx] if cipher_letter_idx != -1 else ""
        decoded_output.append(plain_letter)
    return ''.join(decoded_output)


def encode(input_plain):
    encoded_output = ""
    for p in string.punctuation + " ":
        input_plain = input_plain.replace(p, "")
    for letter in input_plain.lower():
        plain_letter_idx = plain.find(letter)
        cipher_letter = cipher[plain_letter_idx] if plain_letter_idx != -1 else letter
        encoded_output += cipher_letter
    return " ".join([encoded_output[i:i+5] for i in range(0, len(encoded_output), 5)])
