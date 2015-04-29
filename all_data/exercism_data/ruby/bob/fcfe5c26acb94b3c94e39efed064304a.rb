class Bob

  attr_reader :words

  def hey(words)
    @words = words
    if silent?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent?
    words == nil || words.strip.empty?
  end

  def shouting?
    words.upcase == words
  end

  def question?
    words.end_with? '?'
  end

end
