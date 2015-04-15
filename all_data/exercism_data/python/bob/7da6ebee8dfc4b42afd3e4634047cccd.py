"""Bob is a lackadaisical teenager."""


def hey(message):
    """In conversation, his responses are very limited."""
    if not message.strip():
        return "Fine. Be that way!"
    if message.isupper():
        return "Whoa, chill out!"
    if message.endswith("?"):
        return "Sure."
    return "Whatever."
