class Statement
  def initialize(statement)
    @statement = statement.nil? ? "" : statement.strip
  end
 
  def shouted?
    @statement == @statement.upcase
  end

  def question?
    @statement[-1] == '?'
  end

  def nothing?
    @statement.empty?
  end
end

class Bob
  def hey(input)
    statement = Statement.new(input)

    if statement.nothing?
      "Fine. Be that way!"
    elsif statement.shouted?
      "Woah, chill out!"
    elsif statement.question?
      "Sure."
    else
      "Whatever."
    end 
  end
end
