class Bob
  def hey(message)
    case
    when silence?(message)
      'Fine. Be that way.'
    when scream?(message) 
      'Woah, chill out!'     
    when question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?(message)
    message.to_s.empty?
  end

  def scream?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with? '?'
  end

  private :silence?, :scream?, :question?
end
