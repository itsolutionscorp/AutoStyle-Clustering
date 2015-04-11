class Bob
  def hey(input)
    @input = input
    if silence
      'Fine. Be that way!'
    elsif shouting
      'Woah, chill out!'
    elsif question
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence
    @input.strip!
    @input.length == 0
  end

  def shouting
    @input == @input.upcase
  end

  def question
    @input.end_with?("?")
  end

end
