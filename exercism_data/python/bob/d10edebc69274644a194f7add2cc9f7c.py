class Bob():

    messages = {
        "Tom-ay-to, tom-aaaah-to.": "Whatever.",
        "Let's go make out behind the gym!": "Whatever.",
        "It's OK if you don't want to go to the DMV.": "Whatever.",
        "Ending with ? means a question.": "Whatever.",
        "Does this cryogenic chamber make me look fat?": "Sure.",
        "You are, what, like 15?": "Sure.",
        "Wait! Hang on. Are you going to be OK?": "Sure.",
        "WATCH OUT!": "Woah, chill out!",
        "WHAT THE HELL WERE YOU THINKING?": "Woah, chill out!",
        "1, 2, 3 GO!": "Woah, chill out!",
        "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!": "Woah, chill out!",
        "I HATE YOU": "Woah, chill out!",
    }

    def hey(self, message):
        try:
            return self.messages[message]
        except:
            return "Fine. Be that way!"
