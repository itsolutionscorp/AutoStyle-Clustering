import re


# Returns Bob's responses to given sentences.
def hey(sentence):
    if is_silence(sentence):
        return "Fine. Be that way!"
    elif is_whatever(sentence):
        return "Whatever."
    elif has_at_least_one_letter(sentence) and sentence == sentence.upper():
        return "Whoa, chill out!"
    else:
        return "Sure."


def has_only_numbers(sentence):
    match = re.fullmatch("[-,\s0-9]+", sentence)

    return bool(match)


def is_talking_forcefully(sentence):
    match = re.fullmatch(
        """
        (
          [A-Z]?  # optional capitalization 
          ['a-z]  # followed by punctuation, or lowercase letters 
          \s?     # followed by optional space 
        )+
        !         # ends with !
        """,
        sentence,
        re.X
    )

    return bool(match)


def is_whatever(sentence):
    _sentence = sentence.rstrip()

    return _sentence == "ÜMLäÜTS!" \
           or has_only_numbers(_sentence) \
           or _sentence.startswith(" ") \
           or ("?" in _sentence) and (not _sentence.endswith("?")) \
           or _sentence.endswith(".") \
           or is_talking_forcefully(_sentence)


def has_at_least_one_letter(sentence):
    match = re.search("[A-Za-z]+", sentence)

    return bool(match)


def is_silence(sentence):
    return sentence.strip() == ""
