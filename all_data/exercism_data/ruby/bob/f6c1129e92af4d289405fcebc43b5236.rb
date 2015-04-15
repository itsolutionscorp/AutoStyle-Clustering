class Bob
  def hey(text)
    text.strip!
    if text.empty?
      "Fine. Be that way!"
    elsif text =~ /[a-z]/i and text == text.upcase
      "Woah, chill out!"
    elsif text.chars.last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
