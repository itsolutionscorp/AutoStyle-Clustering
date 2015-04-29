# import re
# digit_match = re.compile(r"\d")
# letter_match = re.compile(r"\w")

def hey(text):
    if text.strip() == "":
        return "Fine. Be that way!"

    # no_digits = digit_match.sub(" ", text)
    # has_letters = letter_match.search(no_digits) is not None

    # if has_letters and text.upper() == text:
    if text.isupper():
        return "Whoa, chill out!"

    if text.endswith("?"):
        return "Sure."

    return "Whatever."
