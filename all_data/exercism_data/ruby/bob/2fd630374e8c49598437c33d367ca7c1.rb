class Bob

  def hey(message)
    if silence? message
      'Fine. Be that way.'
    elsif asking? message
      'Sure.'
    elsif shouting? message  
      'Woah, chill out!'
    else 'Whatever.'
    end
  end

  private
  def shouting?(message)
    message.eql? message.upcase
  end

  def asking?(message)
    message.end_with?('?')
  end
 
  def silence?(message)
    message.nil? || message.empty?
  end
end
