class Bob

  def hey message
    message.strip!
    return 'Fine. Be that way!' if message.empty? 
    return 'Woah, chill out!'   if shouting? message
    return 'Sure.'              if message.end_with?('?')
    'Whatever.'
  end
  
  def shouting?(message)
    return false if message[/[a-z]+/]
    return true  if message[/[A-Z]+/]
    false
  end
  
end
