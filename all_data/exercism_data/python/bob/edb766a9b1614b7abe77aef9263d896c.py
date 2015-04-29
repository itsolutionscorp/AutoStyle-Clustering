"""A module providing the Bob functionality."""


def hey(message):
    """The function used to communicate with Bob."""

    stripped_message = message.strip()

    # Not actually saying anything.
    if not stripped_message:
        return "Fine. Be that way!"

    # YELLING.
    if stripped_message.isupper():
        return "Whoa, chill out!"

    # Question?
    if stripped_message[-1] == "?":
        return "Sure."

    return "Whatever."
