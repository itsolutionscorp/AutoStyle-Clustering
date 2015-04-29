class Bob
  def hey(message_string)
    message = Message.new(message_string)

    if message.shouting?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    elsif message.silent?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :message_content

  def initialize(message_content)
    @message_content = message_content
  end

  def shouting?
    message_content =~ /[a-zA-Z]/ && message_content.upcase == message_content
  end

  def question?
    message_content[-1] == '?'
  end

  def silent?
    message_content =~ /\A\s*\z/
  end
end
