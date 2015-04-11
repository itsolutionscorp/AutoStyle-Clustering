class Bob

  def hey message
    message.strip!
    last_index=message.length-1
    return 'Fine. Be that way!' if nothing? message
    return 'Woah, chill out!' if caps?(message, last_index)
    return 'Sure.' if ask?(message[last_index])
    "Whatever."
  end
  
  def nothing? message
  	return true if message==nil || message==''
  	false
  end
  
  def caps? message, last_index
  	for i in 0..last_index
      if letter?(message[i])
        return true if message==message.upcase
      end
    end
    false
  end
  
  def letter?(l)
    l =~ /[[:alpha:]]/
  end
  
  def ask?(ch)
    ch=='?'
  end
  
end
