class Bob
  def hey(message)
    message.strip!
    case true
    when message.empty?
      "Fine. Be that way!"
    when message.upcase == message
      "Woah, chill out!"
    when message.end_with?('?')
      "Sure."
    when message.end_with?('?')
      "Whatever."
    else
      "Whatever."
    end
  end
end
