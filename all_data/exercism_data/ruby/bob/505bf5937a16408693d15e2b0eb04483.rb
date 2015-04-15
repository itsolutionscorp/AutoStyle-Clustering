class Bob
  def hey(msg)
    msg = Message.new(msg || '')

    if msg.silence?
      'Fine. Be that way!'
    elsif msg.yelling?
      'Woah, chill out!'
    elsif msg.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :content

  def initialize(content)
    @content = content.strip
  end

  def yelling?
    content.upcase == content
  end

  def question?
    content.end_with?(??)
  end

  def silence?
    content.strip == ''
  end
end
