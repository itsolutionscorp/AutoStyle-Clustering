#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):

    if (len(what) == 0 or
        what.isdigit() or
        what.isspace()) :
        out_str = "Fine. Be that way!"
    else:
        if (what == what.upper() and
            what != what.lower() and
            not what.isdigit()) :
            out_str = "Whoa, chill out!"
        elif what.find("?",len(what)-1,len(what)) > 0 :
            out_str = "Sure."
        else:
            out_str =  "Whatever."
    return out_str
