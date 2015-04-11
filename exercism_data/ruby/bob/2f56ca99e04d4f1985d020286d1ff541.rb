class Bob 

  def hey(question) 
   if(silence?(question))
    "Fine. Be that way!" 
   elsif(yelling?(question))
     "Woah, chill out!"
   elsif(question?(question))
      "Sure."
   else 
     "Whatever."
   end
  
  end
  
  def yelling?(question)
   if(question == question.upcase && !(question == question.downcase))
     true 
   else false 
   end
  end
  
  def question?(question)
   if(question.end_with? "?")
    true
   else
     false
   end
  end
 
  def silence?(question)
    if(question.strip.empty?)
    true
   else false
   end
  end

end
