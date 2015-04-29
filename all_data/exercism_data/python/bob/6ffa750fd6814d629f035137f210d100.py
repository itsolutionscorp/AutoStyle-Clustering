class Bob(object):
    def hey(self, msg):
        if not msg or not msg.strip():
            return "Fine. Be that way!"
        if msg.upper() == msg and msg.lower() != msg:
            return "Woah, chill out!"
        if msg.endswith('?'):
            return "Sure."
        return "Whatever."
