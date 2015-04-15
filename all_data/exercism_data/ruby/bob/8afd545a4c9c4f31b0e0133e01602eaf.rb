class Bob
  def hey(sentence)
    if being_quiet?(sentence)
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
  def being_quiet?(sentence)
    !sentence || sentence.empty?
  end

  def shouting?(sentence)
    sentence == sentence.upcase
  end

  def asking?(sentence)
    sentence.end_with?("?")
  end
end
