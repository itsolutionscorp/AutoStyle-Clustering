class Bob

  def hey(message)
    if yell?(message)
      "Woah, chill out!" 
    elsif question?(message)
      "Sure." 
    elsif empty?(message)
      "Fine. Be that way!"
    else
      "Whatever." 
    end
  end

  private 

  def upcase?(message)
    message.eql?(message.upcase)
  end

  def question?(message)
    message.end_with?("?")
  end

  def yell?(message)
    contains_character?(message) && upcase?(message)
  end
  
  def empty?(message)
    message =~ /^*\s$/ || message.empty?
  end

  def contains_character?(message)
    message.match /[a-zA-Z]/
  end
end
