class Bob
  def hey(statement)
    s = Statement.new(statement)
    
    if s.is_silent?
      'Fine. Be that way!'
    elsif s.is_exclamatory?
      'Woah, chill out!'
    elsif s.is_interrogatory?
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

  def is_exclamatory?
    @statement == @statement.upcase
  end

  def is_interrogatory?
    @statement.end_with?('?')
  end

  def is_silent?
    @statement.strip.empty?
  end
end
