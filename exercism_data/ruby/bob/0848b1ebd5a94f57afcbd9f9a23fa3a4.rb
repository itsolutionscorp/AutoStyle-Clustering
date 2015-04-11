class Bob
  def hey(message)
    if message.rstrip == ""
      "Fine. Be that way!"
    elsif message.upcase == message && message.match(/[a-zA-Z]+/)
      "Woah, chill out!"
    elsif message.end_with? ('?')
      "Sure."
    else
      "Whatever."
    end
  end
end
