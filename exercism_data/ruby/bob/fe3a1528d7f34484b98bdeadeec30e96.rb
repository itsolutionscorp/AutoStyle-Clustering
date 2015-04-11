class Bob
  def hey input
    return "Fine. Be that way!" if silence?(input) 
    return "Woah, chill out!"   if shouting?(input) 
    return "Sure."              if question?(input)
    return "Whatever."
  end 

  private 
  def silence?(input)
    input.strip.empty?
  end

  def shouting?(input)
    input == input.upcase && input != input.downcase 
  end

  def question?(input)
    input.end_with? "?"
  end
end
