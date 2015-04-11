import re

def hey(msg):
   if msg.isupper():
      return 'Woah, chill out!'
   if msg.endswith('?'):
      return 'Sure.'
   if len(msg.strip()) == 0:
      return 'Fine. Be that way!'
   return 'Whatever.'

if __name__ == "__main__":
  print "hello"
