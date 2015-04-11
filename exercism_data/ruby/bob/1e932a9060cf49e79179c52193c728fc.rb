class Bob

  def hey(statement)
    Statement.new(statement).respond
  end

end

class Statement

  def initialize(statement)
    @statement = statement
  end

  def respond
    if silence?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?
    @statement.nil? || @statement.empty?
  end

  def shouting?
    @statement.upcase == @statement
  end

  def question?
    @statement.end_with? '?'
  end

end
