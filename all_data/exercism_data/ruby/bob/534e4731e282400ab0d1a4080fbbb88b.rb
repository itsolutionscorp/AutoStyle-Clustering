class Bob
  def hey(statement)
    Response.new(Inquiry.new(statement)).respond
  end

end

class Response
  attr_reader :they

  def initialize(inquiry)
    @they = inquiry
  end

  def respond
    case
    when they.said_nothing
      be_blasé
    when they.screamed
      be_chill
    when they.asked_a_question
      be_non_committal
    else
      be_whatever
    end
  end

  def be_blasé
    "Fine. Be that way!"
  end

  def be_chill
    "Woah, chill out!"
  end

  def be_non_committal
    "Sure."
  end

  def be_whatever
    "Whatever."
  end
end

class Inquiry
  attr_reader :statement

  def initialize(statement)
    @statement = statement
  end

  def said_nothing
    statement.to_s.strip.empty?
  end

  def screamed
    statement == statement.upcase
  end

  def asked_a_question
    statement.end_with? "?"
  end
end
