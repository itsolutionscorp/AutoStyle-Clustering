class Bob
  def hey(string)
    if empty?(string)
      "Fine. Be that way!"
    elsif shouting?(string)
      "Woah, chill out!"
    elsif question?(string)
      "Sure."
    else
      "Whatever."
    end
  end
  
  private
  def shouting?(string)
    string.upcase == string
  end
  
  def question?(string)
    string.end_with?('?')
  end
  
  def empty?(string)
    string.strip == ""
  end
end
