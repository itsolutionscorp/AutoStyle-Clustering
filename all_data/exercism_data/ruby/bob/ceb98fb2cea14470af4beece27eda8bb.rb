class Bob

  def hey(message)
    if (message.upcase == message) && (message.downcase != message.upcase)
      "Woah, chill out!"
    elsif message[-1] == "?"
      "Sure."
    elsif message.strip == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
