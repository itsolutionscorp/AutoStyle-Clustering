class Bob

  def hey(heard)
    if silence?(heard)
      return "Fine. Be that way!" 
    elsif shout?(heard)
      return "Woah, chill out!"
    elsif question?(heard)
      return "Sure."
    else
      return "Whatever."
    end
  end

  def silence?(said)
    return said.to_s.strip.empty? #leaving explicit return for readability
  end

  def shout?(said) #leaving function in this objec (no context indicating other object is more appropriate
    return said.upcase == said 
  end

  def question?(said)
    return said.end_with?('?')
  end

end
