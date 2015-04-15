class Bob
  def hey message
    message = Message.new(message.to_s)
    case
    when message.silent? then 'Fine. Be that way!'
    when message.loud? then 'Woah, chill out!'
    when message.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class Message
  attr_reader :text

  def initialize text
    @text = text
  end

  def silent?
    text.strip.empty?
  end

  def loud?
    text == @text.upcase
  end

  def question?
    text.end_with? '?'
  end
end
