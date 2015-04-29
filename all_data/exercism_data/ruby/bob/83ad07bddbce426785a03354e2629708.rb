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

  private

  def shouting?(sentence)
    sentence == sentence.upcase
  end

  def silence?(sentence)
    sentence.to_s.empty?
  end

  def question?(sentence)
    sentence.end_with?("?")
  end
end
