class Bob

  def hey(message)
    message = MessageContent.new(message)

    case
    when message.silence?
      'Fine. Be that way.'
    when message.question?
      'Sure.'
    when message.shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

end

class MessageContent

  def initialize(message)
    self.content = message.to_s
  end

  def silence?
    content.strip.empty?
  end

  def question?
    content.end_with?('?')
  end

  def shouting?
    content.upcase == content
  end

  private
    attr_accessor :content

end
