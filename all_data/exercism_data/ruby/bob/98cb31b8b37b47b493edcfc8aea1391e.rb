class Bob
  def hey(message)
    case message
    when nil, ''
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
