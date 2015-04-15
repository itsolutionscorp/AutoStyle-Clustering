class Bob
  def hey(sentence)
    message = Message.new sentence

    case
    when message.silent?
      "Fine. Be that way!"
    when message.shout?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message

  attr_reader :message

  def initialize(message)
    @message = message
  end

  def silent?
    message.strip.empty?
  end

  def shout?
    message =~ /[A-Z]/ && message.upcase == message
  end

  def question?
    message.end_with? '?'
  end
end
