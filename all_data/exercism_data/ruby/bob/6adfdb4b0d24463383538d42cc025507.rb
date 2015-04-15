class Bob
  def hey string
    message = Message.new(string)

    if message.yelling?
      "Woah, chill out!"
    elsif message.is_a_question?
      "Sure."
    elsif message.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Message
  attr_reader :message
  def initialize string
    @message = string
  end
  def is_a_question?
    @message.end_with? '?'
  end

  def yelling?
    @message == @message.upcase && has_letters?
  end

  def silence?
    @message.strip.empty?
  end

  def has_letters?
   @message.count("a-zA-Z") > 0
  end

end
