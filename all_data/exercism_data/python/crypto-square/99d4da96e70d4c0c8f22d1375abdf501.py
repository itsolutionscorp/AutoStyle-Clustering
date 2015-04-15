from __future__ import division
from math import sqrt, ceil
import string

def encode(text):
    """ Encode the provided text using the square code cipher. """
    text = text.lower()
    text = _remove_unwanted_characters(text)
    width = _compute_encoding_width(text)
    return "".join(_generate_encoded_stream(text, width))

def _compute_encoding_width(text):
    """ Computes the width to use for an encoding rectangle.  This is the
    width of the first rectangle that has enough capacity to hold all the
    characters of the provided text.
    """
    return int(ceil(sqrt(len(text))))

def _generate_encoded_stream(text, width):
    """ Generates the characters for the encoded text, which are the
    characters as produced by a rectangular walk, intermitted by
    a space after each five characters.
    """
    for idx, character in enumerate(_walk_rectangular(text, width)):
        if idx and not idx % 5:
            yield " ";
        yield character

def decode(encoded):
    """ Decode the provided encoded text using the square code cipher. """
    encoded = _remove_spaces(encoded)
    if encoded != _remove_unwanted_characters(encoded):
        raise ValueError("Encoded data invalid")
    width = _compute_decoding_width(encoded)
    encoded = _fill_up_rectangle_rows(encoded, width)
    return "".join(_generate_decoded_stream(encoded, width))

def _compute_decoding_width(encoded):
    """ Computes the width to use for a decoding rectangle.  For decoding,
    we are working on a mirror of the encoding rectangle, so the height of
    the encoding rectangle is returned as the decoding width here.
    """
    encoding_width = _compute_encoding_width(encoded)
    return int(ceil(len(encoded) / encoding_width))

def _fill_up_rectangle_rows(encoded, width):
    """ Since we work on a mirror of the encoding rectangle for decoding,
    empty positions at the end of the rectangle (in the last row only)
    end up as one character short rows in the mirror version.  To be able
    to do a rectangular walk, the short rectangle rows are filled up
    with dummy values here.

    AXXC            AXB              AXB
    XXXX --mirror-> XXX --fill up--> XXX
    BX              XX               XX*
                    CX               CX*
    """
    height = int(ceil(len(encoded) / width))
    capacity = width * height
    incomplete_rows = capacity - len(encoded)
    encoded = list(encoded)
    for offset in range(0, incomplete_rows):
        encoded.insert(len(encoded) - offset * width, None)
    return encoded

def _generate_decoded_stream(encoded, width):
    """ Generates the characters for the decoded text, which are the
    characters as produced by a rectangular walk, skipping the dummy
    values that were added for filling up the decoding rectangle.
    """
    for character in _walk_rectangular(encoded, width):
        if character is not None:
            yield character

def _walk_rectangular(text, width):
    """ Returns the characters in the text, as if the text were laid out in
    a rectangular grid of the provided width, reading down the columns from
    left to right.
    """
    for column in range(0, width):
        for text_offset in range(column, len(text), width):
            yield text[text_offset]

def _remove_unwanted_characters(text):
    """ Removes all characters that are not supported by the cipher. """
    valid_characters = string.ascii_lowercase + string.digits
    return "".join(c for c in text if c in valid_characters)

def _remove_spaces(text):
    """ Removes all spaces from the text. """
    return text.translate(None, ' ')
