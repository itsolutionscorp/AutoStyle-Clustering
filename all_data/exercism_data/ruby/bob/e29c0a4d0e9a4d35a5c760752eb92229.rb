class Bob

  def hey(msg)
    message = Message.new(msg)

    if message.is_yelling?
      'Woah, chill out!'
    elsif message.is_a_question?
      'Sure.'
    elsif message.is_blank?
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

  def is_yelling?
    @msg == @msg.upcase && has_letters?
  end

  def is_a_question?
    /\?\z/.match(@msg)
  end

  def has_letters?
    /[a-zA-Z]/.match(@msg)
  end

  def is_blank?
    @msg.empty? || /\s+\z/.match(@msg)
  end
end
