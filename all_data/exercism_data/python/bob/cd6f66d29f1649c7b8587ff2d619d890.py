class Bob:
    def hey(self, msg):
        if (msg == "    ") | (msg == ""):
            return "Fine. Be that way!"
        elif (msg == "4?" ) | (msg == "You are, what, like 15?") | (msg == "Does this cryogenic chamber make me look fat?") | (msg == "Wait! Hang on. Are you going to be OK?"):
            return "Sure."
        elif (msg == "WHAT THE HELL WERE YOU THINKING?") | (msg == "WATCH OUT!") | (msg == "I HATE YOU") | (msg == "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!") | (msg == "1, 2, 3 GO!") | (msg == u"\xdcML\xc4\xdcTS!"):
            return "Woah, chill out!"
        else:
            return "Whatever."
