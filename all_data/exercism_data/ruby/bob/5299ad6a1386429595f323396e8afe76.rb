class Bob

  def hey(input)
    message = Message.new(input)

    if message.silence?
      "Fine. Be that way!"
    elsif message.shouting?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message
  attr_reader :statement

  def initialize(input)
    @statement = input
  end

  def silence?
    statement.strip.empty?
  end

  def shouting?
    statement == statement.upcase
  end

  def question?
    statement.end_with?('?')
  end
end
