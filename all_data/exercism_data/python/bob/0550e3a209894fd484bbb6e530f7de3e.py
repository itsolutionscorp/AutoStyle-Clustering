def hey(msg):
	msg = msg.strip()
	return "Fine. Be that way!" if not msg           else \
		   "Woah, chill out!"   if msg.isupper()     else \
	       "Sure."              if msg.endswith("?") else \
	       "Whatever."








def _hey(msg):
	msg = msg.strip()
	if not msg:
		return "Fine. Be that way!"
	elif msg.isupper():
		return "Woah, chill out!"
	elif msg.endswith("?"):
		return "Sure."

	return "Whatever."

    
