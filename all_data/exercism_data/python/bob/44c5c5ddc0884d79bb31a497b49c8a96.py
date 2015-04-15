class Bob:
    
    SHOUT_REPLY = "Woah, chill out!"
    NONVERBAL_REPLY = "Fine. Be that way!"
    QUESTION_REPLY = "Sure."
    DEFAULT_REPLY = "Whatever."
    
    def hey(self, message):
        self.message = message
        return self.formulate_prioritised_reply()

    def formulate_prioritised_reply(self):
        if self.message_is_shouted():
            return self.SHOUT_REPLY
        if self.message_is_non_verbal():
            return self.NONVERBAL_REPLY
        if self.message_is_a_question():
            return self.QUESTION_REPLY
        return self.DEFAULT_REPLY
    
    def message_is_shouted(self):
        return self.message.isupper()
    
    def message_is_non_verbal(self):
        return (not self.message.strip())
    
    def message_is_a_question(self):
        return self.message.endswith("?")
    
