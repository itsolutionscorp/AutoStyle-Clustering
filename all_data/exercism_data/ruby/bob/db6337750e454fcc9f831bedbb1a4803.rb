class Bob
  def hey(message)
    message = Message.new(message)
    if message.empty?
      'Fine. Be that way!'
    elsif message.yelling?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  Message = Struct.new(:message) do
    def yelling?
      message.upcase == message
    end

    def question?
      message.chars.last == '?'
    end

    def empty?
      message.nil? or message.empty?
    end
  end
end
