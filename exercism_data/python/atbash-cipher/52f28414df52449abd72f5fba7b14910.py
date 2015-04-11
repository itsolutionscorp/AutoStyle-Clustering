import string


def encode(input_string):
    # Make everything lowercase
    input_string = input_string.lower()

    # Create translation table
    alphabet = "abcdefghijklmnopqrstuvwxyz0123456789"
    reverse_alphabet = "zyxwvutsrqponmlkjihgfedcba0123456789"
    translation_table = input_string.maketrans(alphabet, reverse_alphabet)

    # Add mappings from non-alphanum characters to None
    to_strip_string = string.punctuation + string.whitespace
    to_strip = [ord(char) for char in to_strip_string]
    translation_table.update(dict.fromkeys(to_strip, None))

    # Strip punctuation and spaces and permute
    encoded_string = input_string.translate(translation_table)

    encoded_string_with_spaces = ""

    # Add spaces
    for i in range(len(encoded_string) - 1):
        if i % 5 == 0:
            encoded_string_with_spaces += encoded_string[i:i + 5] + " "

    # Return everything except the last space
    return encoded_string_with_spaces[:-1]


def decode(string):
    # Create translation table
    alphabet = "abcdefghijklmnopqrstuvwxyz0123456789"
    reverse_alphabet = "zyxwvutsrqponmlkjihgfedcba0123456789"
    translation_table = string.maketrans(alphabet, reverse_alphabet)
    translation_table[ord(" ")] = None

    decoded_string = string.translate(translation_table)

    return decoded_string
