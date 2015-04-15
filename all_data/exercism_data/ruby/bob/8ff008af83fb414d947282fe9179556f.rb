class Bob
  def hey(message)
    if message === message.upcase && message != message.downcase
      return 'Woah, chill out!'
    elsif message.end_with?('?')
      return 'Sure.'
    elsif message.lstrip.length == 0
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end
