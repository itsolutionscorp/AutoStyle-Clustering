#!/usr/bin/python
letters = [chr(ord("a") + x) for x in range(26)]
numbers = list("0123456789")
mapping = dict(zip(letters + numbers, letters[::-1] + numbers))
def encode(phrase):
    phrase = "".join(filter(lambda x: x in letters+numbers, phrase.lower()))
    return " ".join(["".join([mapping[y] for y in x]) for x in [phrase.lower()[x:5 + x] for x in range(0,len(phrase), 5)]]).strip()

def decode(phrase):
    phrase = "".join(filter(lambda x: x in letters+numbers, phrase.lower()))
    return "".join([mapping[x] for x in phrase])
