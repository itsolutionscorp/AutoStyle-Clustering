class Bob
  def hey(message)  
    return 'Fine. Be that way!' if message.strip.empty?    
    return 'Woah, chill out!'   if message == message.upcase
    return 'Sure.'              if message.end_with('?')
    
    'Whatever.'
  end
end
