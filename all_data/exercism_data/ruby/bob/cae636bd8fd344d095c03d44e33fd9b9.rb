class Bob
  def hey(message)
    case message
    when /^\s*$/, ''
      'Fine. Be that way!'
    when message.upcase
      'Woah, chill out!'
    when /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
