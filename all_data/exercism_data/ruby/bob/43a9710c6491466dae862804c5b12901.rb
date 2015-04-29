class Bob 

  def hey(question) 
   if(question.strip.empty?)
    "Fine. Be that way!" 
   elsif(question == question.upcase && !(question == question.downcase))
     "Woah, chill out!"
   elsif(question.end_with? "?")
      "Sure."
   else 
     "Whatever."
   end
  
  end
  
end
