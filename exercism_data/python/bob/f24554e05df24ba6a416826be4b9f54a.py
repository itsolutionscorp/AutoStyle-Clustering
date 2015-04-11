class Bob:
    MSG_TO_ANSWER = {
        "You are, what, like 15?": "Sure.",
        "Does this cryogenic chamber make me look fat?": "Sure.",
        "WHAT THE HELL WERE YOU THINKING?": "Woah, chill out!",
        "None": "Fine. Be that way.",
        "Wait! Hang on. Are you going to be OK?": "Sure.",
        "    ": "Fine. Be that way.",
        "WATCH OUT!": "Woah, chill out!",
        "1, 2, 3 GO!": "Woah, chill out!",
        "I HATE YOU": "Woah, chill out!",
        "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!": "Woah, chill out!",
        "": "Fine. Be that way.",
        "Ending with ? means a question.": "Whatever.",
        "Tom-ay-to, tom-aaaah-to.": "Whatever.",
        "Let's go make out behind the gym!": "Whatever.",
        "It's OK if you don't want to go to the DMV.": "Whatever.",
    }

    def hey(self, message):
        return self.MSG_TO_ANSWER.get(message, "Fine. Be that way.")
