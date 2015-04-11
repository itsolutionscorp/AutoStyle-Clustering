def hey(txt):
	rf=txt.rfind("?")
	if txt.strip()=="":
		return "Fine. Be that way!"
	elif txt.upper()==txt and txt.upper()!=txt.lower():
		return "Whoa, chill out!"
	elif rf!=-1 and rf==len(txt)-1:
		return "Sure."
	else:
		return "Whatever."
