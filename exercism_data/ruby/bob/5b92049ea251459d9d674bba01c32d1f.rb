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
  def responses
    {
      silence?: 'Fine. Be that way!',
      shouting?: 'Woah, chill out!',
      question?: 'Sure.',
      default?: 'Whatever.'
    }
  end

  def hey(message_string)
    message = Message.new(message_string)
    _, response = responses.detect { |method, _| (message.send(method)) }
    response
  end
end
