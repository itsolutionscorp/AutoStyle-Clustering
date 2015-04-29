def hey(input):
   if input.isupper():
      return 'Woah, chill out!'
   elif not input or input.isspace():
      return 'Fine. Be that way!'
   elif input.endswith('?'):
      return 'Sure.'
   else:
      return 'Whatever.'
