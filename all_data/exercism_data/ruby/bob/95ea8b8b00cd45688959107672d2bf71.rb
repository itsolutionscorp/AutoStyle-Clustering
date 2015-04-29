class Bob

  def hey(text)
    reply_to Statement.new(text)
  end

  def reply_to(statement)
    if statement.empty?
      "Fine. Be that way."
    elsif statement.question?
      "Sure."
    elsif statement.shouting?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

end

class Statement

  attr_reader :content

  def initialize(input)
    @content = input
  end

  def question?
    content.end_with?('?')
  end

  def shouting?
    content == content.upcase
  end

  def empty?
    content.empty?
  end
end
