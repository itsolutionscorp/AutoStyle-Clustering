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
    say == say.upcase
  end

  def silence?(say)
    say.to_s.empty?
  end

  def question?(say)
    say.end_with?("?")
  end
end
