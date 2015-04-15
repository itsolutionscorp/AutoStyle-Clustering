class Bob
  def hey(message)
    case
    when message.nil? || message.empty?
      "Fine. Be that way."
    when message.upcase == message
      "Woah, chill out!"
    when message[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
