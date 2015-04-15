def hey(question):
   question = question.strip()
   if question == '':
      return 'Fine. Be that way!'
   elif question.endswith('?') and not question.isupper():
      return 'Sure.'
   elif question.isupper():
      return 'Woah, chill out!'
   else:
      return 'Whatever.'
