class Bob

  def hey(statement)
    statement = Statement.new(statement)
    respond(statement.type)
  end

  def respond(statement_type)
    responses[statement_type]
  end

  def responses
    {
      "empty" => "Fine. Be that way.",
      "question" => "Sure.",
      "shouting" => "Woah, chill out!",
      "normal" => "Whatever."
    }
  end

end

class Statement

  attr_reader :content

  def initialize(input)
    @content = input
  end

  def type
    if content.empty?
      "empty"
    elsif question?
      "question"
    elsif shouting?
      "shouting"
    else
      "normal"
    end
  end

  def question?
    content.end_with?('?')
  end

  def shouting?
    content == content.upcase
  end
end
