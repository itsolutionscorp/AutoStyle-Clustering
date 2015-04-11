# -*- coding: utf-8 -*-
"""
Created on Fri Oct 03 20:53:50 2014

@author: Richard
"""

import re


def hey(text):
    if text == "" or text == None or re.search(" "*(len(text)-1),text): #check for enmpy text first
        return "Fine. Be that way!"
    elif re.search("[A-Z]{"+str(len(text.replace(" ",""))-1)+"}",text.replace(" ","")): #check to see if capital 
    # letters comprise all of the sentence but the last letter, removed spaces beforehand in order to avoid counting them
        return 'Woah, chill out!'
    elif "!" in text[-2:]: # if there is an exclimation point...
        if re.search("[a-z]",text): #If there are lowercase letters then it's not yelling
            return "Whatever."
        elif re.search("\!",text[-1:]):
            return 'Woah, chill out!'
    elif re.search("\?",text[-1:]):
        return 'Sure.'            
    else: return 'Whatever.' 
