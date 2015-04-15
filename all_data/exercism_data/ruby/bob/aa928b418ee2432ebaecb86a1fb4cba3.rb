class Bob
 def yelling? (message)
   upper = message =~ /[A-Z]/
   lower = message =~ /[a-z]/
   return(!!upper and not !!lower)
 end
 def question? (message)
   matches = message =~ /\?\z/
   return(!!matches)
 end
 def blank? (message)
   characters = message =~ /\S/
   return(!characters)
 end
 def hey (message)
   if yelling? message
     "Woah, chill out!"
   elsif question? message
     "Sure."
   elsif blank? message
     "Fine. Be that way!"
   else
     "Whatever." 
   end
 end
end
