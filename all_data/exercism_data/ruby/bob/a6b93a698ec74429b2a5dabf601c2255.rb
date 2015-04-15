class Bob
  def hey(input)
    case
    when ignored?(input)
      "Fine. Be that way."
    when yelled_at?(input)
      "Woah, chill out!"
    when questioned?(input)
      "Sure."
    else
      "Whatever."
    end
  end
  
  private
    
  def ignored?(str)
    str.nil? || str.empty?
  end
  
  def yelled_at?(str)
    str == str.upcase
  end
  
  def questioned?(str)
    str.end_with?("?")
  end
end
