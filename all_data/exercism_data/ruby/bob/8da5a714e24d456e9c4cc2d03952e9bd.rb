class Bob
  def hey(words)
    case
    when silence?(words)
     "Fine. Be that way!" 
    when question?(words)
      "Sure."
    when shouting?(words)
     "Woah, chill out!" 
    else
      "Whatever."
    end
  end

  private
  
  def question?(words)
    words.end_with?("?") && !shouting?(words)
  end

  def shouting?(words)
    words.upcase == words && has_upcase?(words)
  end

  def has_upcase?(words)
    words.match(/[A-Z]+/)
  end

  def silence?(words)
    words.length == 0 || words.strip.length == 0
  end
  
end
