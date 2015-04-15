class Bob
  def initialize
  end

  def containLetter(message)
    contain = true
    message =~ /\A[[:alnum:]]+\z/
    return contain
  end
  def hey(message)
    if message.strip.length == 0
      return 'Fine. Be that way!'
    elsif message == message.upcase and message.upcase != message.downcase
      return 'Woah, chill out!'
    elsif message[-1, 1] == '?'
      return 'Sure.'
    end
    return 'Whatever.'
  end

end
