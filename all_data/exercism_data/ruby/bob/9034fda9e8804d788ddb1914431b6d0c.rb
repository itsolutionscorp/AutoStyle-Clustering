class Statement
  def initialize(input_string)
    @input_string = input_string
  end

  def shouting?
    @input_string.upcase == @input_string
  end

  def question?
    @input_string.end_with?('?')
  end

  def silence?
    @input_string.strip.empty?
  end    
end

class Bob
  def hey(input_string)
    statement = Statement.new(input_string)
    if statement.silence?
      'Fine. Be that way!'
    elsif statement.shouting?
      'Woah, chill out!'
    elsif statement.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
