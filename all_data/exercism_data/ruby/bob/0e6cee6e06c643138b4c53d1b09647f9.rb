class Bob

  def hey(conversation)
    if silence?(conversation)
      'Fine. Be that way!'
    elsif yelling?(conversation)
      'Woah, chill out!' 
    elsif question?(conversation)
      'Sure.' 
    else
      'Whatever.'
    end 
  end

  def yelling?(conversation)
    conversation.upcase == conversation && conversation =~ /[A-Z]/
  end

  def question?(conversation)
    conversation.end_with?('?')
  end

  def silence?(conversation)
    conversation.strip.length == 0
  end

end
