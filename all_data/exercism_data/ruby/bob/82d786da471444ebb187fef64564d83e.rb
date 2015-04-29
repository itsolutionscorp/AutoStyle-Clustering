class Bob
  def hey(message)
    if all_caps? message
    'Woah, chill out!'
    elsif question? message
      'Sure.'
    elsif empty_call? message
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end  

  def empty_call?(message)
    message.lstrip.empty?
  end

  def all_caps?(message)
    message.upcase == message && message.match(/[a-zA-Z]/)
  end

  def question?(message)
    message.match(/\?\z/)
  end

end
