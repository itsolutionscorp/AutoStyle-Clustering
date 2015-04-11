def hey(what):
    what = what.strip()

    if not what:
        return "Fine. Be that way!"

    if what.upper() == what and what.lower() != what:
        return "Whoa, chill out!"

    if what.endswith("?"):
        return "Sure."

    return "Whatever."
