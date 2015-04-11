class Bob
  def hey(message)
    case message.to_s
    when /\A\s*\Z/
      'Fine. Be that way!'
    when message.upcase
      'Woah, chill out!'
    when /\?\Z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
