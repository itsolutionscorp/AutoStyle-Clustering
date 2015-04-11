def hey(statement):
    """
    hey(str) -> str

    :param statement: The statement made to Bob
    :return: Bobs response as a string
    """
    if statement.isupper():
        return "Whoa, chill out!"

    if not statement.strip():
        return "Fine. Be that way!"

    if statement.endswith("?"):
        return "Sure."

    return "Whatever."
