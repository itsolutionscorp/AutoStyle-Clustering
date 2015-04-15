#!/bin/env python
import re

class Bob:
  def __init__(self):
    self.responses = {
        '\?$':      'Sure.',
        '^[A-Z]*$': 'Woah, chill out!',
        '^$':       'Fine. Be that way.'
        }

  def hey(self,msg):
    for resp in self.responses:
      if re.search(resp, msg):
        return self.responses[resp]
    return 'Whatever.'
