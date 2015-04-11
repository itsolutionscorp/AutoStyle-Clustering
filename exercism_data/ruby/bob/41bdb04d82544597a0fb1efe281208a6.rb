class Bob
  def hey(message)
    if message.nil? || message.empty?
      'Fine. Be that way.'
    elsif message.end_with? '?'
      'Sure.'
    elsif message == message.upcase
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
