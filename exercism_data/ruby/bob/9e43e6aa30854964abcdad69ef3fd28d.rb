class Bob

  def hey(msg)
    mood = MessageMood.new(msg)
    case
    when mood.silence?; "Fine. Be that way."
    when mood.yelling?; "Woah, chill out!"
    when mood.asking?; "Sure."
    else "Whatever."
    end
  end

end

class MessageMood

  def initialize(msg)
    @msg = msg
  end

  def silence?
    msg.nil? or msg.empty?
  end

  def yelling?
    msg == msg.upcase
  end

  def asking?
    msg.end_with?("?")
  end

  private

  attr_reader :msg

end
