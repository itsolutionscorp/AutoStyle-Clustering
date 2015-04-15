class Bob
  def hey(message)
    statement = Statement.new(message)
    if statement.empty?
      "Fine. Be that way."
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
  def initialize(message)
    @message = message
  end

  def empty?
    @message.to_s == ""
  end

  def shouting?
    @message.upcase == @message
  end

  def question?
    @message.end_with?("?")
  end
end
