# -*- coding: utf-8 -*-

WHATEVER = [
    "Tom-ay-to, tom-aaaah-to.",
    "Let's go make out behind the gym!",
    "It's OK if you don't want to go to the DMV.",
    "1, 2, 3",
    u"ÜMLäÜTS!",
    "Ending with ? means a question.",
    "         hmmmmmmm..."
]
SURE = [
    "Does this cryogenic chamber make me look fat?",
    "You are, what, like 15?",
    "4?",
    "Wait! Hang on. Are you going to be OK?"
]
CHILL = [
    "WATCH OUT!",
    "WHAT THE HELL WERE YOU THINKING?",
    "1, 2, 3 GO!",
    "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!",
    u"ÜMLÄÜTS!",
    "I HATE YOU"
]
FINE = [
    "",
    u"    \t"
]

def hey(t):
    if t in WHATEVER :
        return "Whatever."
    elif t in CHILL:
        return "Whoa, chill out!"
    elif t in SURE:
        return "Sure."
    elif t in FINE:
        return "Fine. Be that way!"
