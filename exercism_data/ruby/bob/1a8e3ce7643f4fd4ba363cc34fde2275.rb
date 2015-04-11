class Bob

  def hey(phrase)
    if silence?(phrase)
      'Fine. Be that way!'
    elsif shouting?(phrase)
      'Woah, chill out!'
    elsif asking?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(str)
    str.nil? || str.empty?
  end

  def shouting?(str)
    str == str.upcase
  end

  def asking?(str)
    str.end_with? '?'
  end

end
