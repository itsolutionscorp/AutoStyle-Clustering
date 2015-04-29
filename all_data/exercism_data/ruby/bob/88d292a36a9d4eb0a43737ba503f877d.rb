class Bob
  def only_numbers(string)
    /[\d]/ =~ string 
  end

  def shouting(string)
    string == string.upcase && string =~ /[?!]$/ 
  end

  def only_numbers(string)
    string =~ /^[\d,!]*$/
  end

  def question(string)
    string =~ /.*\?/
  end
  
  def hey(string)
    if shouting(string)
      "Woah, chill out!"
    elsif question(string)
      "Sure."
    else
      "Whatever."
    end
  end
end
