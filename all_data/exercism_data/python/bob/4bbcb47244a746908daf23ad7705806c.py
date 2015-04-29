# -*- coding: utf-8 -*-

# I reply to phrases
def hey ( phrase ):
    # Someone's Excited
    if phrase.isupper():
        return  "Whoa, chill out!"

    # Was That A Question?
    if phrase.endswith( '?' ):
        return  "Sure.";

    # Did I Hear Something?
    if phrase.strip().__len__() == 0:
        return  "Fine. Be that way!"

    # When In Doubt
    return "Whatever."
