class Bob:
    def hey(self, message):
        new_message = message or ""
        return self.hey_message(new_message.strip() or "")

    def hey_message(self, message):
        if not message:
            return "Fine. Be that way!"
        elif message.isupper():
            return "Woah, chill out!"
        elif message.endswith("?"):
            return "Sure."
        return "Whatever."
