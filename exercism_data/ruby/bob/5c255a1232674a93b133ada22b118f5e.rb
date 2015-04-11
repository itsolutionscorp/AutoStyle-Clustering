class Bob

  def hey(words)
    if silence?(words)
      "Fine. Be that way!"
    elsif shouting?(words) && !only_numbers?(words) && !question_with_only_numbers?(words)
      "Whoa, chill out!"
    elsif question?(words)
      "Sure."
    else
      "Whatever."
    end
  end


  def silence?(words)
    words.strip == ''
  end

  def shouting?(words)
    words == words.upcase
  end

  def only_numbers?(words)
    numbers = words.split(%r{,\s*})
    numbers.join == numbers.join.to_i.to_s
  end

  def question_with_only_numbers?(words)
    number = words.length - 2
    shorter_words = words[0..number]
    numbers = shorter_words.split(%r{,\s*})
    numbers.join == numbers.join.to_i.to_s
  end

  def question?(words)
    words.end_with?("?")
  end

end
