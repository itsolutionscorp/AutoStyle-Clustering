class Bob

  def hey message
    if message.strip == ""
      "Fine. Be that way!"
    elsif message.upcase == message
      "Woah, chill out!"
    elsif message[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end

end
