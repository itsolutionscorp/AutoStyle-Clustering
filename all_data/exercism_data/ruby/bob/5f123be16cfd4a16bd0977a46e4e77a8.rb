class Bob
  attr :msg
  attr :empty_message_response, :shouty_message_response, :interrogative_message_response, :default_response

  def hey(msg)
    @msg = msg
    case
    when empty_message?
      empty_message_response
    when shouty_message?
      shouty_message_response
    when interrogative_message?
      interrogative_message_response
    else
      default_response
    end
  end

  def msg
    @msg.to_s
  end

  def empty_message?
    msg.strip.empty?
  end

  def shouty_message?
    msg == msg.upcase && !empty_message?
  end

  def interrogative_message?
    msg.end_with?('?')
  end

  def empty_message_response
    @empty_message_response || 'Fine. Be that way!'
  end

  def shouty_message_response
    @shouty_message_response || 'Woah, chill out!'
  end

  def interrogative_message_response
    @interrogative_message_response || 'Sure.'
  end

  def default_response
    @default_response || 'Whatever.'
  end

end
