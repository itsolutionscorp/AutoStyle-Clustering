class Bob

  def hey(msg)
    
    if msg.strip.empty?
      "Fine. Be that way!"
    elsif msg.eql?(msg.upcase) && msg =~ /[A-Z]/ 
      "Woah, chill out!"
    elsif msg.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  
  end

end
