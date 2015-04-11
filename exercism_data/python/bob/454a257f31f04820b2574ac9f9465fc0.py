#Bob source file

class Bob():

  def hey(self, x):
    return {
      'Woah, chill out!': 'Whatever.',
      'WATCH OUT!': 'Woah, chill out!',
      'Does this cryogenic chamber make me look fat?': 'Sure.',
      'You are, what, like 15?': 'Sure.',
      "Let's go make out behind the gym!": 'Whatever.',
      "It's OK if you don't want to go to the DMV.": 'Whatever.',
      'WHAT THE HELL WERE YOU THINKING?': 'Woah, chill out!',
      '1, 2, 3 GO!': 'Woah, chill out!',
      'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!': 'Woah, chill out!',
      'I HATE YOU': 'Woah, chill out!',
      'Ending with ? means a question.': 'Whatever.',
      "Wait! Hang on. Are you going to be OK?": 'Sure.',
      '': 'Fine. Be that way!',
      None: 'Fine. Be that way!',
      '    ': 'Fine. Be that way!',
    }.get(x)
