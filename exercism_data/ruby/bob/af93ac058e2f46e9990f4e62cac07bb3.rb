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
    return said.to_s.strip.empty? 
  end

  def shout?(said)
    return said.upcase==said 
  end

  def question?(said)
    return said.end_with?('?')
  end

end
