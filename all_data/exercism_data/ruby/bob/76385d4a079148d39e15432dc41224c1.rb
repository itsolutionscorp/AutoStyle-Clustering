class Bob
  def hey(sentence)
    sentence = sentence.to_s
    if sentence.empty?
      "Fine. Be that way!"
    elsif yelling?(sentence)
      "Woah, chill out!"
    elsif question?(sentence)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def yelling?(string)
    string.upcase == string
  end

  def question?(string)
    string.end_with?("?")
  end
end
