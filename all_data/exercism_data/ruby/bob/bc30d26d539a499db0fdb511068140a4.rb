class Bob

  def hey(words)
    if words == ''
      "Fine, be that way."
    elsif words == words.upcase
      "Woah, chill out!"
    elsif words[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end

end
