class Bob 
  def hey(s) 
    if s.nil? or s.empty?
      "Fine. Be that way."
    elsif s.upcase == s
      "Woah, chill out!"
    elsif s =~ /\?$/
      "Sure."
    else
      "Whatever."
    end
  end 
end
