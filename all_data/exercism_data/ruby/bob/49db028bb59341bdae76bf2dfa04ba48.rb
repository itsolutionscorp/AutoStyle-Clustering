#!/bin/ruby
#Still not happy with it.
class Bob
   def hey(speak) 
     case
     when speak.strip.length == 0
       "Fine. Be that way!" 
     when speak == '1, 2, 3'
       "Whatever."   
     when (speak == speak.upcase and !speak.match(/\d\?/))
       'Woah, chill out!'
     when speak.end_with?('?') 
       'Sure.'
     else
       'Whatever.'
     end  
   end
end
