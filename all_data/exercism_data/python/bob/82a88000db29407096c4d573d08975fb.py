class Bob:
    RESPONSES = {
        "silence": "Fine. Be that way!",
        "shout": "Woah, chill out!",
        "question": "Sure.",
        "whatever": "Whatever."
    }

    def hey(self, msg):
        kind = self.analyze(msg)
        return self.RESPONSES[kind]

    def analyze(self, msg):
        if msg == None or msg.strip() == "":
            return "silence"
        elif msg == msg.upper():
            return "shout"
        elif msg.endswith("?"):
            return "question"
        else:
            return "whatever"
