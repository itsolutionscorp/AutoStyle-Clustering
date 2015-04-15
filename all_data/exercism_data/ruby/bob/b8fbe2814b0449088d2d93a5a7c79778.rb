class Bob
  def hey(message)
    case
    when message.empty?
      'Fine. Be that way.'
    when message.end_with?('?')
      'Sure.'
    when message.upcase == message
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
