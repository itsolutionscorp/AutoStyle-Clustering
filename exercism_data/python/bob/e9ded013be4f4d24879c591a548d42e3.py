#!/usr/bin/env python

class Bob():
  __what = 'Whatever.'
  __woah = 'Woah, chill out!'
  __fine = 'Fine. Be that way!'
  __sure = 'Sure.'

  def hey( self, str ):
    if self.__isEmpty__(str):
      return self.__fine

    if self.__isYelling__(str):
      return self.__woah

    if self.__isQuestion__(str):
      return self.__sure

    return self.__what

  def __isYelling__(self, str):
    'The string is yelling it has letters and they are all capitals'
    return str == str.upper() and str != str.lower()

  def __isEmpty__(self, str):
    'The string is empty if it contains no characters other than spaces'
    return len(str.strip()) == 0

  def __isQuestion__(self, str):
    'The string is a question if it ends in a question mark'
    return "?" == str[len(str)-1]
