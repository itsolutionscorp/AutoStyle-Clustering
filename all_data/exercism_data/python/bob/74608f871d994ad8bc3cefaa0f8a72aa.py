class Bob():
    ANSWERS = {
        "question": "Sure.",
        "yell": 'Woah, chill out!',
        "silent": 'Fine. Be that way!',
        "other": 'Whatever.'
    }

    def hey(self, text):
        text = text.replace(" ", "")
        if not text:
            return self.ANSWERS["silent"]
        if str(text).isupper():
            return self.ANSWERS["yell"]
        if text[-1] == "?":
            return self.ANSWERS["question"]
        return self.ANSWERS["other"]
