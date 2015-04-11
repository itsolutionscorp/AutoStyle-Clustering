class Bob

  def hey(message)

    incomming_message = IncomingMessage.new(message)

    if incomming_message.silent?
      'Fine. Be that way!'
    elsif incomming_message.shouting?
      'Woah, chill out!'
    elsif incomming_message.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class IncomingMessage

  attr_reader :message

  def initialize(message)
    @message = message.to_s
  end

  def question?
     message.end_with?('?')
  end

  def shouting?
    message[/[[:upper:]]/] && !message[/[[:lower:]]/]
  end

  def silent?
    message == ''
  end

end
