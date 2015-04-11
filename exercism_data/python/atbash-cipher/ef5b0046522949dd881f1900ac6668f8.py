def atbash(char):
	if char == " ":
		return ""
	if char.isalpha():
		return chr(ord("z")+ord("a")-ord(char))
	return char

def encode(string):
	out = [""]
	for i in string:
		i = i.lower()
		if not i.isdigit() and not i.isalpha():
			continue
		i=atbash(i)
		if len(out[len(out)-1]) < 5:
			out[len(out)-1] += i
		else:
			out.append(i)
	return " ".join(out)

def decode(string):
	return "".join([atbash(i) for i in string])
