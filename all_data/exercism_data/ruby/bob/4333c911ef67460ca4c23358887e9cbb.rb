class Bob
  def hey(hey_str)
    if hey_str.strip.empty?
      "Fine. Be that way!"
    elsif  hey_str == hey_str.upcase
      "Woah, chill out!"
    elsif hey_str.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
