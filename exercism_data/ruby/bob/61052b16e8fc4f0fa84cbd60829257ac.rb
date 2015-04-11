class Bob
  def hey message
    if message && !message.strip.empty?
      said_something message
    else
      'Fine. Be that way!'
    end
  end

private
  def said_something message
    case message
    when message.upcase
      'Woah, chill out!'
    when /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
