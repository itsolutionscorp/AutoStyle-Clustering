class Bob

  def hey(words)
    @words = words
    if silence?
      "Fine. Be that way!"
    elsif shouting? && !only_numbers? && !question_with_only_numbers?
      "Whoa, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?
    @words.strip == ''
  end

  def shouting?
    @words == @words.upcase
  end

  def only_numbers?
    numbers = @words.split(%r{,\s*})
    numbers.join == numbers.join.to_i.to_s
  end

  def question_with_only_numbers?
    number = @words.length - 2
    shorter_words = @words[0..number]
    numbers = shorter_words.split(%r{,\s*})
    numbers.join == numbers.join.to_i.to_s
  end

  def question?
    @words.end_with?("?")
  end

end
