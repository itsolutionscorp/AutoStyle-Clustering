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
    phrase.
      scan(/[a-zA-Z]/).
      all?{|character| character.match(/^[A-Z]$/) }
  end

  def question? phrase
    phrase =~ /\?\s*$/
  end

end
