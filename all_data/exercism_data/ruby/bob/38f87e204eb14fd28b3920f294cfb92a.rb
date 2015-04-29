class Bob
  def hey(message)
    case
    when message.nil? || message == ''
      return "Fine. Be that way."
    when message.end_with?('?')
      return "Sure."
    when message == message.upcase
      return "Woah, chill out!"
    else
      return "Whatever."  
    end
  end
end
