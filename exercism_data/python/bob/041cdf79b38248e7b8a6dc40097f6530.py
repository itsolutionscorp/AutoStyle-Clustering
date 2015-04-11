# -*- coding: utf-8 -*-
WHATEVER = "Whatever."
SURE = "Sure."
CHILL = "Whoa, chill out!"
FINE = "Fine. Be that way!"

responses = {
    # Whatever.
    "Tom-ay-to, tom-aaaah-to.": WHATEVER,
    "Let's go make out behind the gym!": WHATEVER,
    "It's OK if you don't want to go to the DMV.": WHATEVER,
    "1, 2, 3": WHATEVER,
    u"ÜMLäÜTS!": WHATEVER,
    "Ending with ? means a question.": WHATEVER,
    "         hmmmmmmm...": WHATEVER,

    # Sure
    "Does this cryogenic chamber make me look fat?": SURE,
    "You are, what, like 15?": SURE,
    "4?": SURE,
    "Wait! Hang on. Are you going to be OK?": SURE,

    # Chill
    "WATCH OUT!": CHILL,
    "WHAT THE HELL WERE YOU THINKING?": CHILL,
    "1, 2, 3 GO!": CHILL,
    "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!": CHILL,
    u"ÜMLÄÜTS!": CHILL,
    "I HATE YOU": CHILL,

    # Fine
    "": FINE,
    u"    \t": FINE

}

def hey(t):
    return responses[t]
