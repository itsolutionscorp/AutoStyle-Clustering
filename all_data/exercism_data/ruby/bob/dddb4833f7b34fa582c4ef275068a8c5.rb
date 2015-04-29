class Bob

  def hey(input)
    if no_input?(input)
      'Fine, be that way.'
    elsif shouting?(input)
      'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    else 
      'Whatever.'
    end
  end

  def no_input?(input)
    input == ''
  end

  def shouting?(input)
    input == input.upcase
  end

  def question?(input)
    input.end_with?('?')
  end
end
