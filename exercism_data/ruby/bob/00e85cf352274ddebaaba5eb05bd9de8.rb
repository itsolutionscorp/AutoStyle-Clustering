class Bob

  ALL_CAPS = /^[^a-z]*$/
  QUESTION = /.*\?$/
  EMPTY_STRING = /^$/

  NEW_LINE_SEPERATOR = $/
  
  def hey(message)
    
    sanitize(message)
    
    case message    
    when EMPTY_STRING
      "Fine. Be that way!"
    when ALL_CAPS
      'Woah, chill out!'
    when QUESTION
      'Sure.'           
    else
      'Whatever.'
    end    
  end

  private 
    
  def sanitize(message)
    
    if message.nil?
      ""
    else
      message.strip!
      message.delete! NEW_LINE_SEPERATOR
    end
  end
end
