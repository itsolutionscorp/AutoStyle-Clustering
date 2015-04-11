import re

def hey(what):
    what = what.strip()

    # In order to test if the person is shouting at bob
    # we strip all the non letter characheters from what,
    # and see if all the characters in the resulting string are uppercase.
    # Note that str.islower is used here, because Python does not
    # seem to have a regex group for lowercase unicode letters.
    unicode_letters_only = re.sub(r"[^\w]|[\d_]", "", what)
    is_all_upper = all(not c.islower() for c in unicode_letters_only)

    if re.match(r"^\s*$", what):
        return "Fine. Be that way!"
    elif unicode_letters_only and is_all_upper:
        return "Whoa, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
