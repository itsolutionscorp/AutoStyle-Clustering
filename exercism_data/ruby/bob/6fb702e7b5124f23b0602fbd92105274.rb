class Bob
  def hey(message)
    message.gsub!(/\s+/, ' ')
    case
    when message == message.upcase && message.match(/[A-Z]/)
      "Woah, chill out!"
    when message.match(/\?$/)
      "Sure."
    when message.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
