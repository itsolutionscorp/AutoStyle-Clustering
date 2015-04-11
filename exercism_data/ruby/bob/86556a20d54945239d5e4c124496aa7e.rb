class Message
  def initialize(message_string)
    @message_string = message_string
  end

  def silence?
    @message_string.to_s.strip.empty?
  end

  def shouting?
    @message_string.upcase == @message_string
  end

  def question?
    @message_string.end_with? '?'
  end

  def default?
    true
  end
end

class Bob
  RESPONSES = {
      silence?: 'Fine. Be that way!',
      shouting?: 'Woah, chill out!',
      question?: 'Sure.',
      default?: 'Whatever.'
  }

  def hey(message_string)
    message = Message.new(message_string)
    _, response = RESPONSES.detect { |method, _| (message.send(method)) }
    response
  end
end
