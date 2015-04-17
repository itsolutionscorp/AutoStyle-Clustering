class Bob
  def hey(sentence)
    if sentence == "" || sentence.nil?
      "Fine. Be that way!"
    elsif sentence == sentence.upcase
      "Woah, chill out!"
    elsif sentence.end_with? "?"
      "Sure."
    else
      "Whatever."
    end
  end
end