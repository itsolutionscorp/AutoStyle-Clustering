class Bob

  def hey(msg)
    message_mood = MessageMood.new(msg)
    if message_mood.nothing?
      'Fine. Be that way!'
    elsif message_mood.yelling?
      'Woah, chill out!'
    elsif message_mood.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

class MessageMood

  def initialize(msg)
    @msg = msg
  end

  def nothing?
    @msg.strip.empty?
  end

  def yelling?
    @msg.upcase == @msg
  end

  def question?
    @msg.end_with? '?'
  end

end
