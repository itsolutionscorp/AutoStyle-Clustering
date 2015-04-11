class Bob
  def hey(message)
    message ||= ""

    message = Message.new(message)

    case
    when message.shouting?
      "Woah, chill out!"
    when message.question?
      "Sure."
    when message.empty?
      "Fine. Be that way."
    else
      "Whatever."
    end
  end

  private
  class Message < String
    def shouting?
      match /[A-Z]{2,}/
    end

    def question?
      end_with? "?"
    end
  end
end
