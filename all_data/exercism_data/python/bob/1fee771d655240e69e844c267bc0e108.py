#!/usr/bin/env python

class Bob:
    def hey(self, input_message):
        message = Message(input_message)

        if message.is_blank():
            return "Fine. Be that way!"
        if message.is_yelling():
            return "Woah, chill out!"
        if message.is_question():
            return "Sure."
        return 'Whatever.'

class Message:
    text = ""

    def __init__(self, input_message):
        self.text = input_message    

    def is_blank(self):
        return self.text is None or self.text.strip() == ''

    def is_yelling(self):
        return self.text.isupper()

    def is_question(self):
        return self.text.endswith('?')
