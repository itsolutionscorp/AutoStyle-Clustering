class Bob
  def hey(x)
    if x.nil?
      "Fine. Be that way!"
    elsif x.strip.length == 0
      "Fine. Be that way!"
    elsif x.end_with?("?") && x == x.upcase
      "Woah, chill out!"
    elsif x.end_with?("?")
      "Sure."
    elsif x == x.upcase
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
