class Bob
  ANSWERS = {
    whatever: "Whatever.",
    chill_out: "Woah, chill out!",
    sure: "Sure.",
    fine: "Fine. Be that way!",
  }

  def hey(input)
    message = Message.new(input)
    answer = response_to(message)
    respond_with(answer)
  end

  def respond_with(key)
    ANSWERS.fetch(key)
  end

  def response_to(message)
    return :fine if message.empty?
    return :chill_out if message.shouting?
    return :sure if message.question?
    whatever
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
