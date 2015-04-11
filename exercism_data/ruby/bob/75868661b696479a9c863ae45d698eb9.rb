class Bob
  def hey(input)
    if silent?(input)
      'Fine. Be that way!'
    elsif shouting?(input)
      'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def shouting?(input)
    /[a-zA-z]/.match(input) && input == input.upcase
  end

  def question?(input)
    input[-1] == '?'
  end

  def silent?(input)
    input.delete(' ').empty?
  end
end
