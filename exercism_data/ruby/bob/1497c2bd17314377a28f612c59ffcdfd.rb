class Bob

  def hey(sentence)
    if sentence.empty?
      "Fine. Be that way."
    elsif sentence[-1] == "?"
      "Sure."
    elsif sentence == sentence.upcase
      "Woah, chill out!."
    else
      "Whatever."
    end
  end

end
