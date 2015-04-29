# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  response_msgs = ['Whatever.', 'Whoa, chill out!', 'Sure.', 'Fine. Be that way!']
  response_map = {
      'Tom-ay-to, tom-aaaah-to.':response_msgs[0],
      'WATCH OUT!':response_msgs[1],
      'Does this cryogenic chamber make me look fat?':response_msgs[2],
      'You are, what, like 15?':response_msgs[2],
      'Let\'s go make out behind the gym!':response_msgs[0],
      'It\'s OK if you don\'t want to go to the DMV.':response_msgs[0],
      'WHAT THE HELL WERE YOU THINKING?':response_msgs[1],
      '1, 2, 3 GO!':response_msgs[1],
      '1, 2, 3':response_msgs[0],
      '4?':response_msgs[2],
      'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!':response_msgs[1],
      u'ÜMLÄÜTS!':response_msgs[1],
      u'ÜMLäÜTS!':response_msgs[0],
      'I HATE YOU':response_msgs[1],
      'Ending with ? means a question.':response_msgs[0],
      'Wait! Hang on. Are you going to be OK?':response_msgs[2],
      '':response_msgs[3],
      '    \t':response_msgs[3],
      '         hmmmmmmm...':response_msgs[0],
      'What if we end with whitespace?   ':response_msgs[2]
  }
  return response_map[what]
