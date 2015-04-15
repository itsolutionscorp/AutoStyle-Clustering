class Bob
  def hey(sentence)
    if blank? sentence
      "Fine. Be that way!"
    elsif yelled? sentence
      "Woah, chill out!"
    elsif questioned? sentence
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def blank?(sentence)
    sentence.nil? || sentence.empty?
  end

  def yelled?(sentence)
    sentence == sentence.upcase
  end

  def questioned?(sentence)
    sentence.end_with? "?"
  end
end
