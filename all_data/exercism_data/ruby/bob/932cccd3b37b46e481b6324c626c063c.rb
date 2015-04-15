class Bob
  def hey message
    case message.to_s
    when ''
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
