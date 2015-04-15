class Bob
  def hey(statement)
    s = Statement.new(statement)
    
    if s.silent?
      'Fine. Be that way!'
    elsif s.shouting?
      'Woah, chill out!'
    elsif s.question?
      'Sure.'
    else
      "Whatever."
    end
  end
end

class Statement
  def initialize(statement)
    @statement = String(statement)
  end

  def shouting?
    @statement == @statement.upcase
  end

  def question?
    @statement.end_with?('?')
  end

  def silent?
    @statement.strip.empty?
  end
end
