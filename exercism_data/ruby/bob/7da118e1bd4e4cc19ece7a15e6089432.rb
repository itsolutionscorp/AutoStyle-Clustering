class Bob
  def hey(message)
    if message.upcase == message && message.match('[A-Z]+')
      "Woah, chill out!"
    elsif message.end_with? "?"
      "Sure."
    elsif message.empty? || message.strip == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
