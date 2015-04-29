class Bob
  def hey(message)
    if message.match(/[A-Z]/) && message.upcase == message
      "Woah, chill out!"
    elsif message.strip.length == 0
      "Fine. Be that way!"
    elsif message.end_with? '?' 
      "Sure."
    else 
      "Whatever."
    end
  end
end
