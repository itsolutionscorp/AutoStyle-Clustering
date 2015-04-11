class Bob
  def hey(text)
    message = Message.new(text)

    case
    when message.silence?
      'Fine. Be that way!'
    when message.shouting?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def silence?
    text.nil? || text.strip.empty?
  end

  def shouting?
    shouted = text.upcase
    shouted == text && shouted != text.downcase
  end

  def question?
    text.end_with?('?')
  end
end
