class Bob
  attr_accessor :message

  def hey(message)
    self.message = message.to_s

    case
    when message_is_blank?
      'Fine. Be that way.'
    when message_is_a_question?
      'Sure.'
    when message_is_shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

    def message_is_blank?
      message.strip.empty?
    end

    def message_is_a_question?
      message.end_with?('?')
    end

    def message_is_shouting?
      message.upcase == message
    end

end
