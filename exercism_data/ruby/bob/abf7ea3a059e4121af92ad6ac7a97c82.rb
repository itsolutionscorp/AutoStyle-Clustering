class Bob

  def hey(input)
    Message.new(input).response
  end

end


class Message

  def initialize(input = '')
    @message = input
  end

  def response
    case
    when say_nothing?
      'Fine. Be that way!'
    when yelling?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

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
