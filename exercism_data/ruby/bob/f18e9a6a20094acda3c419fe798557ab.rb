class Statement
  def initialize(string)
    @string = string
  end

  def is_silent?
    @string == nil || @string.empty?
  end

  def is_asking_a_question?
    @string[-1,1] == '?'
  end

  def is_shouting?
    @string.upcase == @string
  end

end

class Bob
  def hey(what_was_said)
    statement = Statement.new(what_was_said)
    if statement.is_silent?
      'Fine. Be that way.'
    elsif statement.is_shouting?
      'Woah, chill out!'
    elsif statement.is_asking_a_question?
      'Sure.'
    else
      'Whatever.'
    end

  end

end
