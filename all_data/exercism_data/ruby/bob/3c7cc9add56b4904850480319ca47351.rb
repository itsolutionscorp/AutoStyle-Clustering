class Bob
  def hey(text)
    if text == text.upcase && text != text.downcase
      "Whoa, chill out!"
    elsif text.end_with? '?'
      "Sure."
    elsif text =~ /^\A\s*\Z/
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
