class Test(object):
    def __init__(self):
        self.i = 0

    def strip(self):
        return self.__str__().strip()

    def __str__(self):
        self.i += 1
        if self.i == 1: return ""
        elif self.i == 2: return "A"
        else: return "?"


def hey(sentence):
    sentence = sentence.strip()
    if sentence == "":
    	return "Fine. Be that way!"
    if sentence.isupper():
    	return "Woah, chill out!"
    if sentence[-1] == '?':
    	return "Sure."
    return "Whatever."

if __name__ == "__main__":
	t = Test()
	print hey(t)
	print hey(t)
