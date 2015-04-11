class Bob
  
  def nothing?(say)
   say.nil? || say.empty?
  end  
  
  def shouting?(say)
   say.upcase == say
  end
  
  def question?(say)
    say.end_with?("?")
  end  
  
  
  def hey( say )
    return 'Fine. Be that way.' if nothing?(say)
    return 'Woah, chill out!' if shouting?(say)
    return 'Sure.' if question?(say)
    "Whatever."
  end  
  
end  
