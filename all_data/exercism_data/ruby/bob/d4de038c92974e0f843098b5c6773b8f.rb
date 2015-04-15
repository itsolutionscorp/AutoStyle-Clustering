class Bob
  def hey(message)

    statement = Statement.new(message) 

    if statement.blank?
      "Fine. Be that way!"
    elsif statement.screaming?
      'Woah, chill out!'
    elsif statement.question?
      'Sure.'
    else
      "Whatever."
    end
    
  end
end

class Statement
  def initialize(statement)
    @statement = statement
  end

  def blank?
    @statement.nil? || @statement.strip.empty?
  end

  def screaming?
    @statement == @statement.upcase && /[a-zA-Z]/.match(@statement) 
  end

  def question?
    @statement[@statement.size - 1] == "?"
  end
end
