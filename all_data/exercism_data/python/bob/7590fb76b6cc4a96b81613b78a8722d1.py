class Bob():
    def __init__(self):
        self.responses = {
            "shouting": "Woah, chill out!",
            "question": "Sure.",
            "silence": "Fine. Be that way!",
            "default": "Whatever."        
            }

    def hey(self, message):
        if message == None or len(message.strip()) == 0:
            return self.responses["silence"]

        elif message == message.upper():
            return self.responses["shouting"]

        elif message[-1] == "?":
            return self.responses["question"]

        else:
            return self.responses["default"]
