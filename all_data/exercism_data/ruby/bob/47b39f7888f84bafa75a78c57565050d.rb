class Message
  def initialize(data)
    @data = data.strip
  end

  def question?
    @data.end_with? '?'
  end

  def yelling?
    @data == @data.upcase
  end

  def nothing?
    @data.nil? || @data.strip.empty?
  end
end

class Bob
  def hey(message)
    message = Message.new(message)

    case
    when message.nothing?
      'Fine. Be that way!'
    when message.yelling?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
