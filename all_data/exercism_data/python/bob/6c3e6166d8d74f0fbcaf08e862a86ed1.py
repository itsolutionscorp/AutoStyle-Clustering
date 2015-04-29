import re


def hey(what):
    upper_what = what.upper().strip()
    upper_groups = re.findall(r"(\w+)", upper_what, re.UNICODE)
    no_integers = re.findall(r"([^\d]+)", "".join(upper_groups))
    
    if not upper_what:
        return "Fine. Be that way!"
    if no_integers and upper_what == what:
        return "Whoa, chill out!"
    if upper_what[-1] == "?":
        return "Sure."
    return "Whatever."
