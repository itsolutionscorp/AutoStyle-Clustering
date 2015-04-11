class Bob
  def hey(text)
    if text.strip.empty? then
      return "Fine. Be that way!"
    elsif text.upcase == text and text.downcase != text then
      return "Woah, chill out!"
    elsif text.end_with?("?") then
      return "Sure."
    else
      return "Whatever."
    end
  end
end
