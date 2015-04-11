class Bob
  def hey(message)
    message = message.to_s
    return 'Sure.'              if message.question? 
    return 'Fine. Be that way.' if message.empty?
    return 'Woah, chill out!'   if message.shouted? 
    return 'Whatever.'
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
