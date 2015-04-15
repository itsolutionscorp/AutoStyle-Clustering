class Bob

  def hey(phrase)
    if phrase == phrase.upcase && phrase =~ /[[:alpha:]]/
      "Whoa, chill out!"
    elsif phrase[-1] == '?'
      "Sure."
    elsif phrase.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
