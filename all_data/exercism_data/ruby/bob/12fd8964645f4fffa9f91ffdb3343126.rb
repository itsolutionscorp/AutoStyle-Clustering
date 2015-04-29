class Bob

  def hey(incoming_message)

    message = Message.new(incoming_message)

    if message.silent?
      'Fine. Be that way!'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Message

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
