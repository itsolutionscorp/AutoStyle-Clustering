class Bob 

  def hey(question) 
   if(question == question.upcase && !(question == question.downcase))
     "Woah, chill out!"
   elsif(question.end_with? "?")
      "Sure."
   elsif(question.empty? || question == question.reverse)
    "Fine. Be that way!" 
   else 
     "Whatever."
  end
  
  end
end
