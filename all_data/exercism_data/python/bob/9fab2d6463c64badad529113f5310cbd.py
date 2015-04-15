#!/usr/bin/python
# coding=UTF-8

import regex as re

class Bob(object):

  CAPS_LETTERS_REGEX = re.compile("[\p{Lu}]+")
  LOWER_LETTERS_REGEX = re.compile("[\p{Ll}]+")
  SPACES_REGEX = re.compile("^[\s]*$")

  def has_lower_case_chars(self, message):
    """Has Lower Case Characters.

    Checks if a string has lowercase characters by iterating over it can calling islower(). Seems
    like something a regex could do? Yes. But it appears Python's regex library (from pip) doesn't
    think ä is a regex. And re that comes with python doesn't support unicode categories.

    """
    for char in message:
      if char.islower():
        return True

  def hey(self, message):
    """Hey.

    Returns a string in response to the message passed to it.
    """

    if Bob.SPACES_REGEX.match(message):
      return "Fine. Be that way!"
    if Bob.CAPS_LETTERS_REGEX.search(message) and not (Bob.LOWER_LETTERS_REGEX.search(message) or self.has_lower_case_chars(message)):
      return "Woah, chill out!"
    elif message.endswith("?"):
      return "Sure."

    return "Whatever."
