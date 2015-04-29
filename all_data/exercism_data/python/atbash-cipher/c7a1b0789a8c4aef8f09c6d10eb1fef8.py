import string


def encode(input_string):
    # Make everything lowercase
    input_string = input_string.lower()

    # Strip punctuation and spaces
    to_strip = string.punctuation + string.whitespace
    for char in to_strip:
        input_string = input_string.replace(char, "")

    # Manipulate string
    new_string = letter_manipulation(input_string)

    encoded_string = ""

    # Add spaces
    # We're getting trailing spaces for some reason
    # k, it's because it'll just go through the end of
    # the string even if the trailing index is out of range
    # that's some weird stuff.
    for i in range(len(new_string) - 1):
        if i % 5 == 0:
            # try:
            encoded_string += new_string[i:i + 5] + " "
            # except IndexError:
            #     encoded_string += new_string[i:]

    # Strip trailing space
    encoded_string = encoded_string.strip(" ")

    return encoded_string


def decode(string):
    # Strip the spaces
    string = string.replace(" ", "")

    return letter_manipulation(string)


# This bit is reusable, since we're switching stripped strings
# back-to-front
def letter_manipulation(string):
    alphabet = "abcdefghijklmnopqrstuvwxyz0123456789"
    reverse_alphabet = "zyxwvutsrqponmlkjihgfedcba0123456789"

    indices = [alphabet.index(char) for char in string]
    new_string_chars = [reverse_alphabet[i] for i in indices]
    new_string = "".join(new_string_chars)

    return new_string
