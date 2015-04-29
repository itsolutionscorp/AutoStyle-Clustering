class Bob
  
  def hey text
    case
    when empty?(text)
      "Fine. Be that way."
    when all_caps?(text)
      "Woah, chill out!"
    when question?(text)
      "Sure."
    else
      "Whatever."
    end
  end
  
  def empty? text
    text.nil? || text == ""
  end
 
  def question? text
    text.chars.last == "?"
  end

  def all_caps? text
    text.upcase == text
  end

end
