class Bob
  def issilence(said)
    return said.to_s.strip.empty? 
  end

  def isshout(said)
    return said.upcase==said 
  end

  def isquestion(said)
    return said.end_with?('?')
  end

  def hey(heard)
    if issilence(heard)
      return "Fine. Be that way!"
    elsif isshout(heard)
      return "Woah, chill out!"
    elsif isquestion(heard)
      return "Sure."
    else
      return "Whatever."
    end
  end
end
