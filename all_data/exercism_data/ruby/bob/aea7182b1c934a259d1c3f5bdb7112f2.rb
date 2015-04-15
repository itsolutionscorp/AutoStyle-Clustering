class Bob

  def hey(input)
    @input = input
    if no_input?
      'Fine, be that way.'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else 
      'Whatever.'
    end
  end

  def no_input?
    @input == ''
  end

  def shouting?
    @input == @input.upcase
  end

  def question?
    @input.end_with?('?')
  end
end
