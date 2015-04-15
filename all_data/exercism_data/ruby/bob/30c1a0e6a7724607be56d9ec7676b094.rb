class Bob

  def hey(message)
    case
    when message_is_blank?(message)
      'Fine. Be that way.'
    when message_is_a_question?(message)
      'Sure.'
    when message_is_shouting?(message)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

    def message_is_blank?(message)
      message.to_s.strip.empty?
    end

    def message_is_a_question?(message)
      message.end_with?('?')
    end

    def message_is_shouting?(message)
      message.upcase == message
    end

end
