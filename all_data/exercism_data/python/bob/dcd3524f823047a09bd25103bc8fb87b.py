WHATEVER = "Whatever."
FINE = "Fine. Be that way."
CHILLOUT = "Woah, chill out!"
SURE = "Sure."

class Bob:
    def hey(self, speech):
        answer = WHATEVER
        if speech is None:
            answer = FINE
        speech = speech.strip()
        if len(speech) == 0:
            answer = FINE
        if speech.isupper():
            answer = CHILLOUT
        lastchar = speech[-1]
        if lastchar == "?":
            answer = SURE
        return answer
