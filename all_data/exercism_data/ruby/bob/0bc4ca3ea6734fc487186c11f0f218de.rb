class Bob

  def hey phrase

    if empty?(phrase)
      'Fine. Be that way!'
    elsif shouting?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  protected

  def empty? phrase
    phrase.nil? || phrase.empty?
  end

  def shouting? phrase
    phrase == phrase.upcase
  end

  def question? phrase
    phrase.end_with? '?'
  end

end
