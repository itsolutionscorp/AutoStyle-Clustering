class Bob
  def hey(input=nil)
    respond_like_a_teenager(input)
  end

  private

  def respond_like_a_teenager(input)
    message = Message.new(input.to_s.strip)

    if message.empty?
      "Fine. Be that way!"
    elsif message.is_yelling?
      "Woah, chill out!"
    elsif message.is_a_question?
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
      self.match(/\p{Alpha}/) && ( self.upcase == self )
    end
  end

end
