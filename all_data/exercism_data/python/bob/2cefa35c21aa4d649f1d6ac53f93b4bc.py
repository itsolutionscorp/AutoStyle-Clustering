#
# Python "Bob" exercise. 
#
def hey(what):

     statement = 'Whatever.'
     shouting = 'Whoa, chill out!'
     interogative = 'Sure.'
     silence = 'Fine. Be that way!'

     question = what

     #If we're sent a null string, treat it as silence...    
     if question.isspace() == True or len(question) == 0:
          response = silence
          return response
     #If all ALLCAPS, shouting...
     elif question.isupper() == True:
          response = shouting
          return response
     #If ends in '?', its a question...
     else:
          s = question.split() ###Break the input into individual words,
                               ###get the last word, break it into characters,
                               ###then evaluate. If last char is '?', its
                               ###a question. If not, and it made it this far,
                               ###its a statement.
          for word in s:
               if word == s[-1]:
                    last = word
                    for char in last:
                         if char == last[-1]:
                              if char == "?":
                                   response = interogative
                                   return response
                              else:
                                   response = statement
                                   return response
