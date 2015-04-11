class Bob
  def hey(sentence)
    if is_shouting?(sentence)
      "Woah, chill out!"
    elsif is_question?(sentence)
      "Sure."
    elsif is_silence?(sentence)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private
  def is_question?(sentence)
    '?' == sentence.chomp[-1]
  end

  def is_shouting?(sentence)
    sentence.match(%r([a-zA-Z])) && sentence.upcase == sentence
  end

  def is_silence?(sentence)
    sentence.strip.empty?
  end
end
