class Bob
  def hey(words)
    if words.strip.length == 0  #check if words only contains whitespace
      "Fine. Be that way!"
    elsif words == words.upcase  #check if words is in all caps
      "Woah, chill out!"
    elsif words.end_with?("?")  #check if words is a question
      "Sure."
    else
      "Whatever."
    end
  end
end
