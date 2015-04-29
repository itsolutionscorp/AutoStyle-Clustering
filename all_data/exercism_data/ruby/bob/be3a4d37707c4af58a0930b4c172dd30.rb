class Bob
  def hey(message)
    respond(Message.new(message))
  end

  private

  def respond(message_obj)
    case
    when message_obj.woah?
      woah_message
    when message_obj.sure?
      sure_message
    when message_obj.fine?
      fine_message
    else
      whatever_message
    end
  end

  def woah_message
    "Woah, chill out!"
  end

  def sure_message
    "Sure."
  end

  def fine_message
    "Fine. Be that way!"
  end

  def whatever_message
    "Whatever."
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message.to_s
  end

  def woah?
    message.upcase == message && message =~ /[A-Z]/
  end

  def sure?
    message[-1] == "?"
  end

  def fine?
    message.delete(' ').length == 0
  end
end
