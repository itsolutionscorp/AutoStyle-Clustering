class Bob
  def hey(message)
    message.strip!
    case true
    when message.empty?
      "Fine. Be that way!"
    when message.upcase == message
      "Woah, chill out!"
    when message.rindex('?') == message.length-1
      "Sure."
    when message.rindex('!') == message.length-1
      "Whatever."
    else
      "Whatever."
    end
  end
end
