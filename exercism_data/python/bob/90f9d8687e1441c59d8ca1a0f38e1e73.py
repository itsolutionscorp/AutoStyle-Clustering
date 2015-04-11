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
        if not msg or msg.isspace():
            return "silence"
        elif msg.isupper():
            return "shout"
        elif msg.endswith("?"):
            return "question"
        else:
            return "whatever"
