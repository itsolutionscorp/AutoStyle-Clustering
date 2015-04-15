def hey(what):
    def is_silence():
        return len(what.strip()) == 0

    def is_shouting():
        return any(map(lambda c: c.isalpha(), what)) and what.upper() == what

    def is_question():
        return what.rstrip().endswith("?")

    if is_silence():
        return "Fine. Be that way!"

    if is_shouting():
        return "Whoa, chill out!"

    if is_question():
        return "Sure."

    return "Whatever."
