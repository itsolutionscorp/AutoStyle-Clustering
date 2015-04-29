require 'active_support/core_ext'

class Bob
  def hey(interaction)
    message = MessageParser.new(interaction)

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

  MessageParser = Struct.new(:message) do
    def not_saying_anything?
      message.blank?
    end

    def yelling?
      message.upcase == message
    end

    def question?
      message.end_with?('?')
    end
  end
end
