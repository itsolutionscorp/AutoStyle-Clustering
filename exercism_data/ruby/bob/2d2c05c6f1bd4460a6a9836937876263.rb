class Bob

  def hey(statement_string)
    statement = Statement.new(statement_string)

    if statement.nothing?
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

  def initialize (statement_string)
    @statement = statement_string
  end

  def question?
    @statement.end_with?("?")
  end

  def shouting?
    @statement.upcase.eql?(@statement)
  end

  def nothing?
    @statement.to_s.strip.empty?
  end

end
