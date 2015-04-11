# "Bob"

def hey(what): 
    replies = {"question" : "Sure.",
               "yell"     : "Whoa, chill out!",
               "ignore"   : "Fine. Be that way!",
               "default"  : "Whatever."
              }

    what = what.strip()

    if (what == ''):
        whatClass = "ignore"
    elif (what.isupper()):
        whatClass = "yell"
    elif (what[-1] == '?'):
        whatClass = "question"
    else:
        whatClass = "default"

    return replies[whatClass]
