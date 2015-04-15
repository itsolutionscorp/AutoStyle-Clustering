class Bob
  def hey(sentence)
    if sentence.to_s.empty?
      "Fine. Be that way!"
    elsif sentence.upcase == sentence
      "Woah, chill out!"
    elsif sentence[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
