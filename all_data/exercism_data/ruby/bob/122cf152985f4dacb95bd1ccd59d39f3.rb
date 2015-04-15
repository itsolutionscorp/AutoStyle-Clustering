class Bob
  def hey(said)
    if silence?(said)
      "Fine. Be that way."
    elsif question?(said)
      "Sure."
    elsif shouting?(said)
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def shouting?(say)
    if say == say.upcase
      true
    end
  end
  def silence?(say)
    if say.to_s.empty?
      true
    end
  end
  def question?(say)
    if say.end_with?("?")
      true
    end
  end
end
