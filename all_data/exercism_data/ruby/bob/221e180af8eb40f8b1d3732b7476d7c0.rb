class Bob
  def hey(message)
    message.strip!

    if message.empty?
      "Fine. Be that way!"
    elsif message.upcase == message
      "Woah, chill out!"
    elsif message.end_with?('?')
      "Sure."
    elsif message.end_with?('?')
      "Whatever."
    else
      "Whatever."
    end
  end
end
