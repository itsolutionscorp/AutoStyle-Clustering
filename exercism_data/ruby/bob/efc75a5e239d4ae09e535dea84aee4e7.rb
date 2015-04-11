class Bob

  def hey(words)
    if words == words.upcase && words != ''
      shouting
    elsif words.end_with? '?'
      question
    elsif words.empty?
      empty
    else 
      "Whatever."
    end
  end

  def shouting
    "Woah, chill out!"
  end

  def question
    "Sure."
  end

  def empty
    "Fine. Be that way."
  end


end
