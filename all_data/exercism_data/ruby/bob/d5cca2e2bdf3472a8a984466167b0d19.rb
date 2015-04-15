class Message
  def initialize(message_string)
    @message_string = message_string
  end

  def silence?
    @message_string.nil? || @message_string.empty?
  end

  def shouting?
    @message_string.upcase == @message_string
  end

  def question?
    @message_string.end_with? '?'
  end
end

class Bob
  def initialize
    @responses = [
                  [Proc.new { |message| message.silence? }, 'Fine. Be that way!'],
                  [Proc.new { |message| message.shouting?  }, 'Woah, chill out!'],
                  [Proc.new { |message| message.question? }, 'Sure.'],
                  [Proc.new { |message| true }, 'Whatever.']
                 ]
  end

  def hey(message_string)
    message = Message.new(message_string)
    pair = @responses.detect { |p| p[0].call(message) }
    pair[1]
  end
end
