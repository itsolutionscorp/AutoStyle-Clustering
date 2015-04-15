class Bob
  def hey(sentence)
    if !sentence || sentence.empty?
      "Fine. Be that way!"
    elsif sentence == sentence.upcase
      "Woah, chill out!"
    elsif sentence[-1, 1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
