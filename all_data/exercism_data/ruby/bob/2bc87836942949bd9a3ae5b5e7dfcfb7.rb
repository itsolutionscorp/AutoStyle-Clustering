class Message
  attr_reader :message

  def initialize(phrase='')
    @message = phrase
  end

  def letters?
    message =~ /[A-Z|a-z]/
  end

  def shouting?
    if letters?
      message.upcase == message
    end
  end

  def question?
    message.end_with?("?")
  end

  def silence?
    message.strip.empty?
  end
end

class Bob

  def hey(phrase)
    message = Message.new(phrase)
    case 
    when message.silence?
      "Fine. Be that way!"
    when message.shouting?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
