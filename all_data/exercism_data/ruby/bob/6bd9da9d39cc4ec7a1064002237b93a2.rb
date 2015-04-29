class Bob
  
  def hey message
    if is_nothing? message
      "Fine. Be that way!"
    elsif messing_with_vibe? message
        "Woah, chill out!"
    elsif asking_something? message
      "Sure."
    else
      "Whatever."
    end    
  end
  
  def messing_with_vibe? message
    message.upcase.eql? message and not message[/[a-zA-Z]/].nil?
  end
  
  def asking_something? message
    message.reverse.chr.eql? '?'
  end
  
  def is_nothing? message
    message.strip.empty?
  end
  
end
