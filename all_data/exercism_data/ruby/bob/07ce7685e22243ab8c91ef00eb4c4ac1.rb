class Bob

  def hey(txt)
    return  "Fine. Be that way!" if !txt || txt.strip.empty?
    return "Woah, chill out!" if txt == txt.upcase
    return "Sure." if txt[-1] == '?'
   
    "Whatever."
  end

end
