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

"""
# Atbash Cipher

Create an implementation of the atbash cipher, an ancient encryption system created in the Middle East.

The Atbash cipher is a simple substitution cipher that relies on
transposing all the letters in the alphabet such that the resulting
alphabet is backwards. The first letter is replaced with the last
letter, the second with the second-last, and so on.

An Atbash cipher for the Latin alphabet would be as follows:

```plain
Plain:  abcdefghijklmnopqrstuvwxyz
Cipher: zyxwvutsrqponmlkjihgfedcba
```

It is a very weak cipher because it only has one possible key, and it is
a simple monoalphabetic substitution cipher. However, this may not have
been an issue in the cipher's time.

Ciphertext is written out in groups of fixed length, the traditional group size
being 5 letters, and punctuation is excluded. This is to make it harder to guess
things based on word boundaries.

## Examples
- Encoding `test` gives `gvhg`
- Decoding `gvhg` gives `test`
- Decoding `gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt` gives `The quick brown fox jumps over the lazy dog.`
"""
