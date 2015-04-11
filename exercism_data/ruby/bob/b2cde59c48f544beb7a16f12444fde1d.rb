class Bob

  def hey(input)

    message = Message.new(input)

    case
    when message.silence?
      "Fine. Be that way."
    when message.shouting?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Message

  attr_reader :input

  def initialize(input)
    @input = input.strip
  end

  def shouting?
    input == input.upcase
  end

  def question?
    input.end_with?('?')
  end

  def silence?
    input.empty?
  end

end
