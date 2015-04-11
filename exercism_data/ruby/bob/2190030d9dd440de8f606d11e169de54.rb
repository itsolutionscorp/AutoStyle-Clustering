class Bob
  def hey(supplied_message)
    message = Message.new(supplied_message)

    if message.empty?
      "Fine. Be that way!"
    elsif message.question?
      "Sure."
    elsif message.yelling?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(supplied_message)
    @message = supplied_message
  end

  def empty?
    @message.to_s.strip.empty?
  end

  def question?
    @message.end_with?("?") && !yelling?
  end

  def yelling?
    @message == @message.upcase
  end
end
