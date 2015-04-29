class Bob

  def hey(input)
    @input = Message.new(input)

    case
    when @input.say_nothing?
      'Fine. Be that way!'
    when @input.yelling?
      'Woah, chill out!'
    when @input.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message

  def initialize(input = '')
    @message = input
  end

  def say_nothing?
    @message.strip.empty?
  end

  def yelling?
    @message.eql?(@message.upcase)
  end

  def question?
    @message.end_with?("?")
  end

end
