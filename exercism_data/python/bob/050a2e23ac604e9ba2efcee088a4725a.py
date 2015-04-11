#Travis Eickmeyer
#bob.py

#Descrpition: Lackadaisical teenager responder

def hey(line):
    line = line.strip()
    if len(line)==0:
	return "Fine. Be that way!"
    elif line.isupper():
        return "Whoa, chill out!"
    elif line[len(line)-1] == '?':
	return "Sure."
    else:
	return "Whatever."
