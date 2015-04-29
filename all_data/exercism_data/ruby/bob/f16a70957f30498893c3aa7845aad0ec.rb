class Bob
  def hey(phrase)
    if shouting? phrase
      'Woah, chill out!'
    elsif asking? phrase
      'Sure.'
    elsif silent? phrase
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def shouting?(phrase)
    phrase == phrase.upcase && phrase != phrase.downcase
  end

  def asking?(phrase)
    phrase[-1] == '?'
  end

  def silent?(phrase)
    phrase.strip.empty?
  end
end
