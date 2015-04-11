class Bob
  def hey(message)
    case
    when message.nil? || message.empty?
      'Fine. Be that way.'
    when message.end_with?('?')
      'Sure.'
    when message == message.upcase
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
