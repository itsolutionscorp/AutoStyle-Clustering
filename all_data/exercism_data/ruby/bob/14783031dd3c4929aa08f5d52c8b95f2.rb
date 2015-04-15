class Bob
  def hey(msg)
    message = Message.new(msg)

    case
    when message.silence?
      'Fine. Be that way!'
    when message.shouted?
      'Woah, chill out!'
    when message.asked?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(msg)
    @msg = msg || ''
  end

  def silence?
    @msg.empty?
  end

  def shouted?
    @msg !~ /[a-z]/
  end

  def asked?
    @msg.end_with?('?')
  end
end
