class Bob

  def hey(msg)
    message = Message.new(msg)

    if message.yelling?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    elsif message.nothing?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(msg)
    @msg = msg
  end

  def yelling?
    @msg == @msg.upcase && letters?
  end

  def question?
    /\?\z/.match(@msg)
  end

  def letters?
    /[a-zA-Z]/.match(@msg)
  end

  def nothing?
    @msg.empty? || /\s+\z/.match(@msg)
  end
end
