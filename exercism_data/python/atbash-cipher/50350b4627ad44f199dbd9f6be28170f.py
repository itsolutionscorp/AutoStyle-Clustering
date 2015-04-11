import string

# Create translation table
translation_table = ''.maketrans(string.ascii_letters,
                                 string.ascii_lowercase[::-1] * 2,
                                 string.punctuation + string.whitespace)


def encode(input_string):

    # Strip punctuation and spaces and permute
    encoded_string = input_string.translate(translation_table)

    # Add spaces
    encoded_string_chunks = [encoded_string[i:i + 5]
                             for i in range(0, len(encoded_string), 5)]

    encoded_string_with_spaces = " ".join(encoded_string_chunks)

    # Return everything except the last space
    return encoded_string_with_spaces


def decode(encoded_string):

    decoded_string = encoded_string.translate(translation_table)

    return decoded_string
