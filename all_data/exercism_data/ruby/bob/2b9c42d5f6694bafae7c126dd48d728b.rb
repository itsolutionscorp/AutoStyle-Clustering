class Statement
  attr_reader :statement

  def initialize(statement)
    @statement = statement
  end

  def shout?
    statement.match(/[A-Z]/) && statement == statement.upcase
  end

  def question?
    statement.end_with? "?"
  end

  def silence?
    statement.strip == ""
  end
end

class Bob
  def hey(input)
    statement = Statement.new input
    return "Woah, chill out!"   if statement.shout?
    return "Sure."              if statement.question?
    return "Fine. Be that way!" if statement.silence?    
    "Whatever."
  end
end
