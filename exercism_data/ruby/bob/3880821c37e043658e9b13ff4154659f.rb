class Bob
  def hey(input)
    message = Message.new(input)
    respond_to(message)
  end

  def respond_to(message)
    answers.map { |answer| break answer.response if answer.match?(message) }
  end

  def answers
    [Fine.new, Chill.new, Sure.new, Whatever.new]
  end
end

class Sure
  def match?(message)
    message.question?
  end

  def response
    "Sure."
  end
end

class Chill
  def match?(message)
    message.shouting?
  end

  def response
    "Woah, chill out!"
  end
end

class Fine
  def match?(message)
    message.empty?
  end

  def response
    "Fine. Be that way!"
  end
end

class Whatever
  def match?(message)
    true
  end

  def response
    "Whatever."
  end
end

class Message
  attr_reader :string
  def initialize(string)
    @string = string.to_s
  end

  def empty?
    string.strip.empty?
  end

  def shouting?
    string.upcase == string
  end

  def question?
    string.end_with? "?"
  end
end
