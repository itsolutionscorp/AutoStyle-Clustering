class Bob
  def hey(message)
    case
    when message == message.upcase && message.match(/[A-Z]/m)
      "Woah, chill out!"
    when message.gsub("\n", " ").match(/\?$/)
      "Sure."
    when message.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
