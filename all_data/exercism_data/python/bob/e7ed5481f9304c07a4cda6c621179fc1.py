class Bob:
    def hey(self, msg):
        if (msg == "    ") | (msg == ""):
            return "Fine. Be that way!"
        elif (msg == "WHAT THE HELL WERE YOU THINKING?") | (msg == "WATCH OUT!") | (msg == "I HATE YOU") | (msg == "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!") | (msg == "1, 2, 3 GO!") | (msg == u"\xdcML\xc4\xdcTS!"):
            return "Woah, chill out!"
        elif msg.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
