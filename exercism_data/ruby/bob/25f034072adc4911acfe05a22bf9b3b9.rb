class Bob

  def hey(statement)
    statement = Statement.new(statement)
    statement.response
  end

end

class Statement

  attr_reader :content

  def initialize(input)
    @content = input
  end

  def response
    if content.empty?
      'Fine. Be that way.'
    elsif question?
      'Sure.'
    elsif shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  def question?
    content.end_with?('?')
  end

  def shouting?
    content == content.upcase
  end
end
