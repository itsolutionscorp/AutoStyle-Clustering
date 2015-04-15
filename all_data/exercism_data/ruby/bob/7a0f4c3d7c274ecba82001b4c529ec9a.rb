class Bob
  def hey(text)

    message = Message.new(text)

    case
    when message.silence?
      'Fine. Be that way.'
    when message.yell?
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
  def question?
    text.end_with?('?')
  end
  def silence?
    text.nil? || text.empty?
  end
  def yell?
    text == text.upcase
  end
end
