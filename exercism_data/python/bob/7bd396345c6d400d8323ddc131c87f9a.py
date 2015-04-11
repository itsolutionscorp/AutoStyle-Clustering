# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import re



def hey(message):

	if not message or re.search(r"^[\s]+$", message, flags=0):
		return 'Fine. Be that way!'

	elif (re.search(r"[\d]+[?]", message,flags=0) or
		(re.search(r"([A-Z]??[a-z]+[ !.,]??[ ]?[^ ])+[0-9A-Z]*[?]",message,flags=0))):
		return 'Sure.'

	elif ((not re.search(r"([a-z]+|[äü]+)", message, flags=0)) and
		re.search(r"[0-9, ]*(?=[A-ZÜÄ]+)([A-ZÜÄ0-9 !%^*@#$(]+)[!?]?", message, flags=0)):
		return 'Whoa, chill out!'

	else:
		return 'Whatever.' 
