class Bob
  def hey(string)
    string = string.strip
    if string == string.upcase && !string.empty?
      "Woah, chill out!"
    elsif string.end_with?("?")
      "Sure."
    elsif string.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
