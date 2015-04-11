class Bob
  def hey(input_string)
    if silence?(input_string)
      'Fine. Be that way!'
    elsif shouting?(input_string)
      'Woah, chill out!'
    elsif question?(input_string)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def shouting?(input_string)
    input_string.upcase == input_string
  end

  def question?(input_string)
    input_string.end_with?('?')
  end

  def silence?(input_string)
    input_string.strip.empty?
  end
end
