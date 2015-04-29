class Bob
  def hey(message = nil)
    case message
    when /\?$/
      'Sure'
    when message.upcase
      'Woah, chill out!'
    when nil
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
