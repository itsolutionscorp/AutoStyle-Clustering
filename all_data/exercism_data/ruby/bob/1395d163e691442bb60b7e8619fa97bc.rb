class Bob
  def hey(str)
    if str.nil? || "" == str 
    "Fine. Be that way."    
    elsif str[-1,1] == "?"
      "Sure."    
    elsif str.upcase == str
      "Woah, chill out!" 
    else
      "Whatever."
    end
  end
end
