class Bob

  def hey(input)
    if silent?(input)
      'Fine. Be that way.'
    elsif shouting?(input)
      'Woah, chill out!'
    elsif asking_question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def shouting?(input)
    input == input.upcase
  end

  def asking_question?(input)
    input.end_with? '?'
  end

  def silent?(input)
    input.empty?
  end

end
