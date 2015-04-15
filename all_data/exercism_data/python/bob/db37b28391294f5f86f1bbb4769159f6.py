class Bob:
    phrases_response = {
        "Tom-ay-to, tom-aaaah-to.": "Whatever.",
        "Does this cryogenic chamber make me look fat?": "Sure.",
        "WATCH OUT!": "Woah, chill out!",
        "You are, what, like 15?": "Sure.",
        "Let's go make out behind the gym!": "Whatever.",
        "Woah, chill out!": "WHAT THE HELL WERE YOU THINKING?",
        "It's OK if you don't want to go to the DMV.": "Whatever.",
        'WHAT THE HELL WERE YOU THINKING?': 'Woah, chill out!',
        '1, 2, 3 GO!': 'Woah, chill out!',
        '1, 2, 3': 'Whatever.',
        '4?': 'Sure.',
        'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!': 'Woah, chill out!',
        u"\xdcML\xc4\xdcTS!": 'Woah, chill out!',
        u"\xdcML\xe4\xdcTS!": 'Whatever.',
        'I HATE YOU': 'Woah, chill out!',
        'Ending with ? means a question.': 'Whatever.',
        "Wait! Hang on. Are you going to be OK?": 'Sure.',
        '': 'Fine. Be that way!',
        '    ': 'Fine. Be that way!'
    }

    def hey(self, string):
        if string in self.phrases_response:
            return self.phrases_response[string]
        else:
            return "Don't understand"
