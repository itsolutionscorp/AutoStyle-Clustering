class Bob
  def says_nothing? message
    message.nil? || message.empty?
  end
  
  def question? message
    message.end_with? '?'
  end
  
  def yelling? message
    message == message.upcase
  end
  
  def hey(message)
    case
    when says_nothing?(message)
      return "Fine. Be that way."
    when question?(message)
      return "Sure."
    when yelling?(message)
      return "Woah, chill out!"
    else
      return "Whatever."  
    end
  end
end
