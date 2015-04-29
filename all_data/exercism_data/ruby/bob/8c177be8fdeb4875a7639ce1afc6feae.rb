class Bob
  def hey(statement)
    statement = Statement.new(statement)
    if statement.silence?
      "Fine. Be that way!"
    elsif statement.shouting?
      "Woah, chill out!"
    elsif statement.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Statement
  attr_reader :statement
  def initialize(statement)
    @statement = statement
  end

  def shouting?
    statement == statement.upcase
  end

  def silence?
    statement.to_s.strip.empty?
  end

  def question?
    statement.end_with?("?")
  end
end
