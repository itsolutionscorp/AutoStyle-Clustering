class Bob

  def hey inquiry
    case 
    when surly?(inquiry)
      "Fine. Be that way!"
    when yelling?(inquiry)
      "Woah, chill out!"
    when questioning?(inquiry)
      "Sure."
    else
      "Whatever."
    end
  end
  
  def questioning? s
    s.end_with? "?"
  end

  def yelling? s
    s.upcase == s && s =~ /[A-Z]/
  end

  def surly? s
    s.strip.empty?
  end
end
