class Bob
  def hey(text)
    message = Message.new(text)
    case
    when message.nothing? then 'Fine. Be that way!'
    when message.shout? then 'Woah, chill out!'
    when message.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class Message
  def initialize(text)
    @text = text.to_s
  end

  def nothing?
    @text.empty?
  end

  def shout?
    !nothing? && @text == @text.upcase
  end

  def question?
    @text.end_with? '?'
  end
end
