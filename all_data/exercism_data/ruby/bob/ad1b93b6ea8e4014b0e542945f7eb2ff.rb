class Bob

  def hey(message)
    case
    when message.strip.empty?
      "Fine. Be that way!"
    when message.upcase == message
      "Woah, chill out!"
    when message.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end

end
