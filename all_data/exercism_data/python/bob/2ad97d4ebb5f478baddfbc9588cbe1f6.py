__author__ = 'matth_000'


def hey(arg):

    #get rid of any whitespace
    arg = arg.strip()

    #we've stripped out all whitespace and there is nothing there
    if arg == "":
        return u"Fine. Be that way!"

    #look for the yelling (second clause filters out non-alpha questions)
    if arg == arg.upper() and arg != arg.lower():
        return u"Whoa, chill out!"

    #look for a question
    if arg[-1] == "?":
        return u"Sure."

    #anything else
    return u"Whatever."
