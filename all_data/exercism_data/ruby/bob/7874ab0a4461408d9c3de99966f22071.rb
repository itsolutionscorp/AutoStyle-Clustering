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
    !input.empty?
  end

  def shouting?(input)
    !input.empty? && input.upcase == input
  end

  def question?(input)
    input.end_with?("?")
  end
end
