class Bob
  def hey(phrase)
    if silence? phrase
      'Fine. Be that way!'
    elsif shouting? phrase
      'Woah, chill out!'
    elsif question? phrase
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?(phrase)
    phrase.strip.empty?
  end

  def shouting?(phrase)
    stripped = phrase.gsub(/[^a-zA-Z]/, '')
    !stripped.empty? && stripped.upcase == stripped
  end

  def question?(phrase)
    phrase.end_with? '?'
  end
end
