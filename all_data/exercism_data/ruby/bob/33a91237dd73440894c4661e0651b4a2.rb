class Bob
  def hey(sentence)
    if sentence.to_s.empty?
      "Fine. Be that way!"
    elsif shouting?(sentence)
      "Woah, chill out!"
    elsif asking?(sentence)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def shouting?(sentence)
    sentence.upcase == sentence
  end

  def asking?(sentence)
    sentence.end_with? "?"
  end
end
