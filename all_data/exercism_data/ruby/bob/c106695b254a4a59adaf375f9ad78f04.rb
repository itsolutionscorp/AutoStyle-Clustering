class Bob

  def hey(words)
  
    if words.lstrip.empty?
      return "Fine. Be that way!"
      
    elsif words == words.upcase
      return "Woah, chill out!"
      
    elsif words.end_with? "?"
    return "Sure."
      
    else
      return "Whatever."
    end
    
  end
  
end
