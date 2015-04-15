class Bob
  def hey(sentence)
    if is_empty?(sentence)
      "Fine. Be that way!"
    elsif is_shouting?(sentence)
      "Woah, chill out!"
    elsif is_question?(sentence)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def is_empty?(sentence)
    !sentence || sentence.empty?
  end

  def is_shouting?(sentence)
    sentence == sentence.upcase
  end

  def is_question?(sentence)
    sentence.end_with?("?")
  end
end
