def hey(statement):
    """
    hey(str) -> str

    :param statement: The statement made to Bob
    :return: Bobs response as a string
    """
    if "".join(ch for ch in statement if ch.isalpha()).isupper():
        return "Whoa, chill out!"

    if statement.isspace() or len(statement) == 0:
        return "Fine. Be that way!"

    if statement.endswith("?"):
        return "Sure."

    return "Whatever."
