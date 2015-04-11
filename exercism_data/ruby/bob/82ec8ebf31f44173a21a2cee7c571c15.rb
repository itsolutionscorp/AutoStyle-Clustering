class Bob
  def hey(message)
    case message
    when message.nil? || message.empty?
      'Fine. Be that way.'
    when message.end_with?('?')
      'Sure.'
    when message.eql?(message.upcase)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
