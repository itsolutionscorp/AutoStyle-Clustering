class Bob

  def hey( comment )
    return be_that_way if silent?( comment )
    return chill if shouted?( comment )
    return sure if questioning?( comment )
    whatevs
  end

  private
  
  def whatevs
    "Whatever."
  end
  
  def sure
    "Sure."
  end
  
  def chill
    "Woah, chill out!"
  end
  
  def be_that_way
    "Fine. Be that way!"
  end
  
  def silent?( message )
    return true if message.empty?
    characters = message.chars.uniq    
    return true if ( (characters.size == 1) && (characters.first == " ") )
  end

  def shouted?( message )
    return false unless ( message =~ /[a-zA-z]/ )
    return true if ( message == message.upcase )
  end

  def questioning?( message )
    return true if ( message =~ /\?\Z/)
  end

end
