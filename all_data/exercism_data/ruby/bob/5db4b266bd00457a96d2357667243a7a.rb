class Bob
  def hey(message=nil)
    respond_like_a_teenager(message)
  end

  private

  def respond_like_a_teenager(message)
    msg = Message.new(message.to_s.strip)

    if msg.empty?
      "Fine. Be that way!"
    elsif msg.is_yelling?
      "Woah, chill out!"
    elsif msg.is_a_question?
      "Sure."
    else
      "Whatever."
    end
  end

  class Message < String
    def is_a_question?
      self.end_with?('?')
    end

    def is_yelling?
      self.upcase == self
    end
  end

end
