# -*- coding: utf-8 -*-

class Bob:

    @staticmethod
    def hey(message: str) -> str:
        msg = message.strip()
        if not msg:
            return "Fine. Be that way!"
        elif msg.isupper():
            return "Woah, chill out!"
        elif msg.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
