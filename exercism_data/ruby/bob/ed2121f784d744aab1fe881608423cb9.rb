class Bob
  def hey(interaction)
    message = Message.new(interaction)

    case
    when message.not_saying_anything?
      "Fine. Be that way!"
    when message.yelling?
      "Woah, chill out!"
    when message.question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  Message = Struct.new(:message) do
    def not_saying_anything?
      String(message).strip.empty?
    end

    def yelling?
      message.upcase == message
    end

    def question?
      message.end_with?('?')
    end
  end
end
