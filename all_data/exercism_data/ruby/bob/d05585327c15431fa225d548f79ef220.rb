class Bob
  def hey(s)
    if s.strip.empty?
      "Fine. Be that way!"
    elsif s == s.upcase
      "Woah, chill out!"
    elsif s.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
