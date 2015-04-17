class Bob
  def hey(message)
    case
    when message.nil?, message.empty?
      'Fine. Be that way.'
    when message == message.upcase
      'Woah, chill out!'
    when message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end