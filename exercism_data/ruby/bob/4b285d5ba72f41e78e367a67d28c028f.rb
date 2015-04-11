class Bob

  def hey(words)
  
    if words.lstrip.empty?
      "Fine. Be that way!"
      
    elsif words == words.upcase
      "Woah, chill out!"
      
    elsif words.end_with? "?"
      "Sure."
      
    else
      "Whatever."
    end
  end
end
