class Bob:

    def hey(self, text):
        # At first test if there is said anything at all
        if text.strip() =="":
            return "Fine. Be that way!"
        # Then test if Bob is shouted at
        elif text.isupper():
            return "Woah, chill out!"
        # Detect questions
        elif text[-1] == "?":
            return "Sure."
        # At last handle everthing else if nothing from above applies
        else:
            return "Whatever."
