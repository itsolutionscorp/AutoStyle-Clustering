class Bob
  def hey(heard)
    if heard===nil || heard.rstrip==="" 
      return "Fine. Be that way!"
    elsif heard.upcase===heard
      return "Woah, chill out!"
    elsif heard[-1]==="?"
      return "Sure."
    else
      return "Whatever."
    end
  end
end
