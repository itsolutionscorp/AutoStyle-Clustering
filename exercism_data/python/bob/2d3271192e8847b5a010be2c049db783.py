def hey(what):
    s = "".join(what.split())
    if not s:
        return "Fine. Be that way!"
    if s.upper() == s and s.lower() != s:
        return "Whoa, chill out!"
    if s[-1] == "?":
        return "Sure."
    return "Whatever."
