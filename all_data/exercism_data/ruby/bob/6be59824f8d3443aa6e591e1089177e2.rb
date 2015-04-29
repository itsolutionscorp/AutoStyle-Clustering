class Bob
  def hey(message)
    if silence(message)
      'Fine. Be that way!'
    elsif message == message.upcase
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(message)
    message.strip.length == 0
  end
end
