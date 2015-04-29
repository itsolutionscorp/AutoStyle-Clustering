from string import maketrans, punctuation


PLAIN  = "abcdefghijklmnopqrstuvwxyz"
CIPHER = "zyxwvutsrqponmlkjihgfedcba"
is_punctuation = (lambda letter: letter in punctuation)

def decode(message):
    decode = maketrans(CIPHER, PLAIN)
    decoded_message = message.lower().translate(decode)
    formatted_decoded_message = ""
    for letter in decoded_message:
        if letter.isspace():
            next
        elif letter in CIPHER:
            formatted_decoded_message += letter
    return formatted_decoded_message

def encode(message):
    encode = maketrans(PLAIN, CIPHER)
    encoded_message = message.lower().translate(encode)
    formatted_encoded_message = ""
    letter_count = 0 # the output is required to be in blocks of 5, letter_count is used to keep track and then add a " " to make the next block of 5.

    for letter in encoded_message:
        if letter.isspace() or is_punctuation(letter):
            next
        elif (letter in CIPHER or letter.isdigit()) and letter_count < 5:
            formatted_encoded_message += letter
            letter_count += 1
        elif letter_count == 5:
            formatted_encoded_message += " " + letter
            letter_count = 1

    return formatted_encoded_message
