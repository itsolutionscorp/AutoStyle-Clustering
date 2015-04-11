class Bob
  attr_reader :input

  def hey(input)
    @input = input

    if silent?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def shouting?
    /[a-zA-z]/.match(input) && input == input.upcase
  end

  def question?
    input[-1] == '?'
  end

  def silent?
    input.strip.empty?
  end
end
