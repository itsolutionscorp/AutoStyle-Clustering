class Bob
  def hey(message)
    message = Message.new(message)

    if message.silence?
      'Fine. Be that way!'
    elsif message.yelling?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(message)
    @message = message
  end

  def yelling?
    @message.each_char.all? { |c| c.upcase == c }
  end

  def question?
    @message[-1] == '?'
  end

  def silence?
    @message.strip == ''
  end
end
