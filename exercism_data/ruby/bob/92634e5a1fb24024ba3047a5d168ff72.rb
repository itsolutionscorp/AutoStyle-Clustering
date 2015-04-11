class Bob
  def hey(str)
    if !str.scan(/[A-Z]/).empty? && str == str.upcase
      "Woah, chill out!"
    elsif str.end_with?("?")
      "Sure."
    elsif str.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
