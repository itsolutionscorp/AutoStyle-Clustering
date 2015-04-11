class Bob

  def hey(phrase)
    if phrase.strip == ''
      "Fine. Be that way!"
    elsif phrase.match(/.*[a-zA-Z].*/) and phrase == phrase.upcase
      "Woah, chill out!"
    elsif phrase.split('').last == "?"
      "Sure."
    else
      "Whatever."
    end
  end

end
