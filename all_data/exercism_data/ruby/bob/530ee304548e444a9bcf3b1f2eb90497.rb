class Bob
  def hey(message)
    message = Message.new(message)
    return 'Woah, chill out!' if message.shouted? && message.contains_words?
    return 'Sure.' if message.question?
    return 'Fine. Be that way!' unless message.something_said?
    'Whatever.'
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message.to_s
  end

  def shouted?
    message.upcase == message 
  end

  def contains_words?
    message[/[a-zA-Z]+/]
  end

  def question?
    message[-1] == '?'
  end

  def something_said?
    !message[/\A\s*\Z/]
  end
end
