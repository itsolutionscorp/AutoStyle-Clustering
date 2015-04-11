class Bob
  def hey(input)
    if shouting?(input)
      'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    elsif statement?(input)
      'Whatever.'
    else
      'Fine, be that way.'
    end
  end

  def statement?(input)
    input.length > 0
  end

  def shouting?(input)
    !input.empty? && input.upcase == input
  end

  def question?(input)
    input[-1] == "?"
  end
end
