class Bob
  def hey(message)
    case message
    when nil, ''
      return "Fine. Be that way."
    when /\?$/
      return "Sure."
    when message.upcase
      return "Woah, chill out!"
    else
      return "Whatever."  
    end
  end
end
