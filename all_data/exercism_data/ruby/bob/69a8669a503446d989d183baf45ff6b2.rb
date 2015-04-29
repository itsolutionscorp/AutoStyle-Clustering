class Bob
  def hey(text)
    if is_shouting?(text)
      "Woah, chill out!"
    elsif text.end_with?("?")
      "Sure."
    elsif text.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def is_shouting?(text)
    letters = text.chars.grep /[[:alpha:]]/
    !letters.empty? && letters.all? {|c| c.upcase == c}
  end
end
