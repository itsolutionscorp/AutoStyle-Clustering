# -*- coding: utf-8 -*-

#'Silence' case: "Fine. Be that way!"
#  <input is empty> OR <input contains only whitespace>
#
#'Yelling' case: "Whoa, chill out!"
#  <input contains something other than whitespace> AND {
#    { <input does not contain lower case letters> AND <input contains one or more capital letters> } OR
#    { <input does not contain any letters> AND <input ends with an exclamation point> }
#  }
#
#'Question' case: "Sure."
#  <input contains something other than whitespace> AND <input ends with a question mark> AND { 
#    <input contains one or more lower case letters> OR <input does not contain any letters>
#  }
#
#'Everything Else' case: "Whatever."
#  <input fails the other three cases>

from unicodedata import normalize

def hey(input) :
  if not input : # <input is empty>
    return "Fine. Be that way!"
  elif input.isspace() : # <input contains only whitespace>
    return "Fine. Be that way!"
  else : # <input contains something other than whitespace>
    
    input = normalize('NFKD', input).encode('ASCII', 'ignore') # convert any unusual unicode chars into similar ASCII chars
    input = list(input) # Python strings are immutable, so we convert input to a list of chars
    while input[len(input) - 1] == " " :
      del input[len(input) - 1] # this removes any whitespace from the end of input
    input = "".join(input) # convert input back to a string

    no_lc = True # no_lc tells us if input does not contain lower case letters
    lowercase = list("abcdefghijklmnopqrstuvwxyz")
    for letter in lowercase :
      no_lc = (input.count(letter) == 0) and no_lc

    no_cap = True # no_cap tells us if input does not contain capital letters
    capital = list("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
    for letter in capital :
      no_cap = (input.count(letter) == 0) and no_cap

    if no_lc : # <input does not contain lower case letters>
      if no_cap : # <input does not contain any letters>
        if input[len(input) - 1] == "!" : # <input ends with an exclamation point>
          return "Whoa, chill out!"
        elif input[len(input) - 1] == "?" : # <input ends with a question mark>
          return "Sure."
        else : # <input fails the other three cases>
          return "Whatever."
      else : # <input contains one or more capital letters>
        return "Whoa, chill out!"
    else : # <input contains one or more lower case letters>
      if input[len(input) - 1] == "?" : # <input ends with a question mark>
        return "Sure."
      else : # <input fails the other three cases>
        return "Whatever."
