class Bob
  def hey(message)
    message = Message.new(message)

    respond_to message
  end

  private
    def respond_to(message)
      if message.silence?
        "Fine. Be that way!"
      elsif message.shouting?
        "Woah, chill out!"
      elsif message.question?
        "Sure."
      else
        "Whatever."
      end
    end
end

class Message
  def initialize(message)
    @contents = message
  end

  def shouting?
    @contents == @contents.upcase
  end

  def question?
    @contents.end_with? '?'
  end

  def silence?
    @contents.strip.empty?
  end
end
