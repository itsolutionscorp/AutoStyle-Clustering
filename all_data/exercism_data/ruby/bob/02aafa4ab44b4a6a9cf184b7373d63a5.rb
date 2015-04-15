class Bob
  def hey(input)
    message = Message.new(input)
    respond_to(message)
  end

  def respond_to(message)
    return fine if message.empty?
    return chill_out if message.shouting?
    return sure if message.question?
    whatever
  end

  def whatever
    "Whatever."
  end

  def chill_out
    "Woah, chill out!"
  end

  def sure
    "Sure."
  end

  def fine
    "Fine. Be that way!"
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
    string[-1] == "?"
  end
end
