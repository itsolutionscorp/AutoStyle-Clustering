class Bob
  def hey(inputString)
    if silence?(inputString)
      'Fine. Be that way!'
    elsif shouting?(inputString)
      'Woah, chill out!'
    elsif question?(inputString)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def shouting?(inputString)
    inputString.upcase == inputString
  end

  def question?(inputString)
    inputString.end_with?('?')
  end

  def silence?(inputString)
    inputString.strip.empty?
  end
end
