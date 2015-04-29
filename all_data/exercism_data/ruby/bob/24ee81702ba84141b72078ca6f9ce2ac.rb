class Bob
  def hey(msg)
    message = Message.new(msg)

    case
    when message.silent?
    then 'Fine. Be that way!'
    when message.shouting?
    then 'Woah, chill out!'
    when message.question?
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

  def silent?
    msg.empty?
  end

  def shouting?
    msg.upcase == msg
  end

  def question?
    msg.end_with? "?"
  end

end
