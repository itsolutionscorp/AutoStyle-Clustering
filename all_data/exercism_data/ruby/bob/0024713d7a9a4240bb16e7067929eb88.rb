class Bob
  def hey(message)
    if message.nil? || message.strip == ''
      'Fine. Be that way!'
    elsif message == message.upcase
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
