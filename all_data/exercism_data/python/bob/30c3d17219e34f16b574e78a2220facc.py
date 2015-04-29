#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    num = "1,2,3,4,5,6,7,8,9,0"
    
    if what is None or len(what) < 2 or "\t" in what or "\n" in what:
        return "Fine. Be that way!"
    
    if what == what.upper():
        if "?" in what:
            return "Sure."

        else: 
            for i in what:
                if i in num and "!" not in what:
                    return "Whatever."
            return "Whoa, chill out!"

    else:
        what = what.lower()

    
    if (("what" in what or "who" in what or "where" in what or 
        "when" in what or "why" in what or "how" in what or
        "does" in what) and "?" in what) or "?" in what[-1]:
        return "Sure."

    else:
        return "Whatever."
