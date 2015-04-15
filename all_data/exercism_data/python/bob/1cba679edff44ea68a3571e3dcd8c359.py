class Bob():
    def hey(self, msg):
        msg = msg.strip()
        if len(msg) == 0:
            return 'Fine. Be that way!'
        # Second test makes sure there is any letter that can go .upper()
        if msg == msg.upper() and msg != msg.lower():
            return 'Woah, chill out!'
        if msg[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
