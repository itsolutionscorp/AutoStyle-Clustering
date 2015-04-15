class Bob
  def hey(input)
    if empty?(input)
      'Fine, be that way.'
    elsif question?(input)
      'Sure.'
    elsif shouting?(input)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  def empty?(input)
    input.length == 0
  end

  def shouting?(input)
    input.upcase == input
  end

  def question?(input)
    input.end_with?("?")
  end
end
