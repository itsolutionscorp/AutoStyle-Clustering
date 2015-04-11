#
# Skeleton file for the Python "Bob" exercise.
#
NOTHING ="Fine. Be that way!"
QUESTION = "Sure."
SHOUT = "Whoa, chill out!"
TALK = "Whatever."


def hey(what):
    #some sanitation
    #removing whitespace
    what = what.strip()
    #testing for empty strings
    if what == "":
        return NOTHING #bob's response to silence
    #numbers cause problems; let's deal with them now
    if ord(what[0]) < 65:
        if what[-1] == '?': #Questions end with a question mark
            return QUESTION
        if what[-1] =='!': #our usual trick with 'upper' doesn't apply here
            return SHOUT
        return TALK
    #questions end in a question mark
    if what[-1] == '?':
        #allcaps means shouting: bob doesn't like that
        if what.upper()==what:
            if what[0] is int:
                return QUESTION
            return SHOUT
        return QUESTION
    #we shout with all caps on the internet; simple test to see if this is
    #the case
    if what.upper() == what:
        return SHOUT

    return TALK
