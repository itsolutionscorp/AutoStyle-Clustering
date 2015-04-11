class Bob
  def hey(value)
    message = Message.new(value)
    
    case 
    when message.question?
      'Sure.'
    when message.empty?
      'Fine. Be that way.'
    when message.shouted? 
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(content)
    @content = content.to_s
  end

  def empty?
    content.empty?
  end

  def question?
    content.end_with? '?'
  end

  def shouted?
    content.upcase == content
  end

private

  def content
    @content
  end
end
