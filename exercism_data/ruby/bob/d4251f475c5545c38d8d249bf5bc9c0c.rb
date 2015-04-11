class Bob
  def hey(conversation)
    if meaningless?(conversation)
      "Fine. Be that way!"      
    elsif yelling?(conversation)
      "Woah, chill out!"
    elsif asking?(conversation)
      "Sure."
    else
      "Whatever."
    end
  end
  
  private
  def yelling?(conversation)
    conversation == conversation.upcase && conversation.match(/[A-Z]/)
  end
  
  def asking?(conversation)
    conversation.end_with?("?")
  end
  
  def meaningless?(conversation)
    conversation.strip.empty?
  end  
end
