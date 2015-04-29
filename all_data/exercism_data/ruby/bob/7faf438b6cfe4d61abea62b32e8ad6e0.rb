class Bob
  def hey(str)
    if str.strip.empty?
      "Fine. Be that way!"
    elsif (str.upcase == str) && str.slice(/[A-Z]+/)
      "Woah, chill out!"
    elsif str.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
