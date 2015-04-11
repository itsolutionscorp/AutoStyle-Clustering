class Bob
  def hey(msg)
    message = Message.new(msg)

    case
    when message.is_silent?
    then 'Fine. Be that way!'
    when message.is_shouting?
    then 'Woah, chill out!'
    when message.is_question?
    then 'Sure.'
    else 'Whatever.'
    end

  end
end

class Message
  attr_reader :msg

  def initialize str
    @msg = str.strip
  end

  def is_silent?
    msg.empty?
  end

  def is_shouting?
    msg.upcase == msg
  end

  def is_question?
    msg.end_with? "?"
  end

end
