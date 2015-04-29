class Bob
  def hey(input)
    if statement?(input)
      'Whatever.'
    elsif shouting?(input)
      'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    else
      'Fine, be that way.'
    end
  end

  def statement?(input)
    !input.empty?
  end

  def shouting?(input)
    !input.empty? && input.upcase == input
  end

  def question?(input)
    input.end_with?("?")
  end
end
