class Bob
  @@default_message = 'Fine. Be that way.'

  def hey(message)
    case
    when message == nil
      @@default_message
    when message.empty?
      @@default_message
    when matches_question?(message)
      'Sure.'
    when matches_yelling?(message)
      'Woah, chill out!'
    when matches_whatever?(message)
      'Whatever.'
    else
      @@default_message
    end
  end

  private

  def matches_question?(message)
    message.end_with?('?')
  end

  def matches_whatever?(message)
    message.end_with?('.') or message.end_with?('!')
  end

  def matches_yelling?(message)
    message.upcase == message
  end
end
