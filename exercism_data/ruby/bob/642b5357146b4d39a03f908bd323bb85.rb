class Bob

  def hey(sentence)
    if sentence.empty?
      "Fine. Be that way."
    elsif sentence.end_with? == "?"
      "Sure."
    elsif sentence == sentence.upcase
      "Woah, chill out!."
    else
      "Whatever."
    end
  end

end
