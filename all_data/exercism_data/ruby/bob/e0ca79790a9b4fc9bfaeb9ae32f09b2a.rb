class Bob
  def hey(text)
    if text == text.upcase 
      "Woah, chill out!"
    elsif text.end_with? '?'
      "Sure."
    elsif text.strip == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
         
end
