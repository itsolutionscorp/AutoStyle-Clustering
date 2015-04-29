#
# Skeleton file for the Python "Bob" exercise.
#
import string
import unicodedata
from collections import namedtuple


StringComponents = namedtuple('StringComponents',
                              "uppercase_chars, lowercase_chars, special_chars")


def strip_accents(s):
    """
    strips accents for umlauts
    """
    return unicodedata.normalize('NFD', s)


def split_to_components(input_string):
    """
    splits the string into its basic components after stripping
    accents and whitespaces
    """
    string_components = StringComponents([], [], [])
    cleaned_string = strip_accents(input_string.strip())

    for char in cleaned_string:
        if char in string.ascii_uppercase:
            string_components.uppercase_chars.append(char)
        elif char in string.ascii_lowercase:
            string_components.lowercase_chars.append(char)
        else:
            string_components.special_chars.append(char)

    return string_components


def hey(what):
    sc = split_to_components(what)

    if not sc.lowercase_chars and sc.uppercase_chars:
        return "Whoa, chill out!"
    if sc.special_chars and sc.special_chars[-1] == '?':
        return "Sure."
    if not (sc.lowercase_chars or sc.uppercase_chars or sc.special_chars):
        return "Fine. Be that way!"

    return "Whatever."
