class Bob

  attr_reader :input

  def hey(input)
    @input = input
    determine_statement_type
  end

  def determine_statement_type
    if empty?
      'Fine, be that way.'
    elsif question?
      'Sure.'
    elsif shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  def shouting?
    input == input.upcase
  end

  def question?
    input[-1, 1] == '?'
  end

  def empty?
    input == ''
  end
end
