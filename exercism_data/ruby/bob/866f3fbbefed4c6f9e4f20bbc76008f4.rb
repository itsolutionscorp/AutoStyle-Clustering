class BobMessage
  def initialize message
    @message = message
  end

  def is_shout?
    @is_shout ||= @message.upcase == @message
  end

  def is_silent?
    @is_silent ||= @message.strip.length == 0
  end

  def is_question?
    @is_question ||= @message[-1] == '?'
  end
end

class Bob
  def hey msg
    message = BobMessage.new msg
    respond_to_message(message)
  end

  private

  def respond_to_message msg
    if msg.is_silent?
      "Fine. Be that way!"
    elsif msg.is_shout?
      "Woah, chill out!"
    elsif msg.is_question?
      "Sure."
    else
      "Whatever."
    end
  end
end
