class Bob
  def hey(string)
    if string.nil? || string.empty?
      return "Fine. Be that way."
    elsif string.end_with?("?")
      return "Sure."
    elsif string == string.upcase
      return "Woah, chill out!"
    else 
      return "Whatever."
    end
  end
end
