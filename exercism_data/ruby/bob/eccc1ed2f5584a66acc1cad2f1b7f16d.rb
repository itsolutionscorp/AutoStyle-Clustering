class MessageParser
  def initialize message
    @message = message
  end

  def empty?
    @message.strip.empty?
  end

  def shouting?
    @message.upcase == @message
  end

  def asking?
    @message[@message.length-1] == "?"
  end
end

class Bob
  def hey message
    parsed_message = MessageParser.new message

    if parsed_message.empty?
      "Fine. Be that way!"
    elsif parsed_message.shouting?
      "Woah, chill out!"
    elsif parsed_message.asking?
      "Sure."
    else
      "Whatever."
    end
  end
end
