class Bob
  def hey(statement_text)
    statement = Statement.new(statement_text)
    if statement.silent?
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
  attr_reader :statement_text

  def initialize(text)
    @statement_text = text
  end

  def question?
    statement_text.end_with?("?")
  end

  def shouting?
    return !self.silent? && statement_text == statement_text.upcase
  end

  def silent?
    return statement_text !~ /[^[:space:]]/
  end
end
