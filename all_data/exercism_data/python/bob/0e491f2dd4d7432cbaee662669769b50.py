# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import re
def hey(text):
	text = text.strip()
	if text.endswith("?") and not text.isupper():
		return 'Sure.'
	elif text.isupper():
		return 'Whoa, chill out!'
	elif len(text) == 0:
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
