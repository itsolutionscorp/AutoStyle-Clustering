class Bob
  def hey(message)
    message_to_s = message.to_s
    
    if message_to_s.question? 
      'Sure.'
    elsif message_to_s.empty?
      'Fine. Be that way.'
    elsif message_to_s.shouted? 
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class String
  def question?
    end_with? '?'
  end

  def shouted?
    upcase == self
  end
end
