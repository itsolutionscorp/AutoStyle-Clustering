# -*- coding: utf-8 -*-
class Bob(object):
	def hey(self, msg):
		print msg
		if not msg or msg is None or msg.isspace():
			return 'Fine. Be that way!'
		elif msg.isupper():
			return 'Woah, chill out!'
		elif msg[len(msg)-1:] == '?':
			return 'Sure.'
		else:
			return 'Whatever.'
