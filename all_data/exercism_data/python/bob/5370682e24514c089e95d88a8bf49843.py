# -*-coding: utf-8-*-

import re

def hey(str=""):

    if(re.search("^[ \t\r\n]*$", str)):
        return "Fine. Be that way!"
    elif(re.search("^[ÜÄA-Z0-9 ,%^*@#$()!]+!+$|^[A-Z ]*\??$", str)):
        return "Whoa, chill out!"
    elif(str[-1] == "?"):
        return "Sure."
    else:
        return "Whatever."
