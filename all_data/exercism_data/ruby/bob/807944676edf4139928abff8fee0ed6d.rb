class Message

  def initialize message
    @message = message
  end

  def silent?
    @message.strip.empty?
  end

  def question?
    @message.end_with?('?')
  end

  def yell?
    upcase? && !only_numbers?
  end

  private

  def only_numbers?
    clean(@message).empty?
  end

  def upcase?
    @message == @message.upcase 
  end

  def clean(message)
    # string without numbers, coma and question mark
    message.gsub(/\d|\W/,'')
  end
end

class Bob

  def hey(message_heard)
    respond(Message.new(message_heard))
  end

  private

  def respond(message)
    case
    when message.silent?
      "Fine. Be that way!"
    when message.yell?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
