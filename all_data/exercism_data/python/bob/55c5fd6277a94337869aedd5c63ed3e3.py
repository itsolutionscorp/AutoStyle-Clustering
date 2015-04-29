def hey(what):
    s = "".join(what.split())
    if not s:
        return "Fine. Be that way!"
    if s.upper() == s and s.lower() != s:
        return "Whoa, chill out!"
    if s.endswith("?"):
        return "Sure."
    return "Whatever."
