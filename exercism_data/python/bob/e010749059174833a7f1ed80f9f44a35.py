#
# Skeleton file for the Python "Bob" exercise.
#

# Importing string module for string.letters for use in has_letters.
import string


def hey(what):
    """
    hey function reads the input string (what) and returns one of four possible
    answers:
    1. Input: silence.
       Return: "Fine. Be that way!"
    2. Input: question.
       Return: "Sure."
    3. Input: yelling.
       Return: "Whoa, chill out!"
    4. Input: everything else
       Return: "Whatever."
    """

    # Checking for silence. We also have to strip the whitespace away.
    if len(what.strip()) == 0:
        return "Fine. Be that way!"

    # Checking for questions:
    # 1. Question mark has to be at the end.
    # 2. If the question is shouted (all caps), it is not counted as a question.
    # 2.a Numbers have no caps, so we check for non-letters only string.
    elif what.strip().endswith("?") and (what.upper() != what or not has_letters(what)):
        return "Sure."

    # Checking for yelling:
    # 1. We check for all caps.
    # 2. String has to have letters or the last char has to be a question mark.
    elif what.upper() == what and (has_letters(what) or what.strip().endswith("!")):
        return "Whoa, chill out!"

    # The answer for everything else.
    else:
        return "Whatever."


# Function that checks if the string has letters in it.
def has_letters(what):

    for l in string.letters:
        if what.find(l) != -1:
            return True
