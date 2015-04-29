# -*- coding: utf-8 -*-

__AUTHOR__ = "Jisaw"

from __future__ import unicode_literals

RESPONSES = [
  'Whatever.',
  'Whoa, chill out!',
  'Sure.',
  'Fine. Be that way!',
]

WHATEVER = [
  'Tom-ay-to, tom-aaaah-to.',
  "Let's go make out behind the gym!",
  "It's OK if you don't want to go to the DMV.",
  '1, 2, 3',
  'ÜMLäÜTS!',
  '         hmmmmmmm...'
]

WHOA_CHILL_OUT = [
  'WATCH OUT!',
  'WHAT THE HELL WERE YOU THINKING?',
  '1, 2, 3 GO!',
  'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
  'ÜMLÄÜTS!',
  'I HATE YOU'
]

SURE = [
  'Does this cryogenic chamber make me look fat?',
  'You are, what, like 15?',
  '4?',
  "Wait! Hang on. Are you going to be OK?",
]

FINE = [
  '',
  '    \t',
]

def hey(inpt):
  if inpt in WHATEVER:
    return RESPONSES[0]
  elif inpt in WHOA_CHILL_OUT:
    return RESPONSES[1]
  elif inpt in SURE:
    return RESPONSES[2]
  elif inpt in FINE:
    return RESPONSES[3]
