import re


def hey(what):
    what = "" if what is None else what.strip()

    if what == "":
        return u"Fine. Be that way!"
    elif what.upper() == what and re.search(r"[^\d\s\.\?,:;]", what):
        return u"Whoa, chill out!"
    elif what.endswith("?"):
        return u"Sure."

    return u"Whatever."
