class Bob

  NOTHING_RESPONSE = "Fine. Be that way!"
  QUESTION_RESPONSE = "Sure."
  YELL_RESPONSE = "Woah, chill out!"
  DEFAULT_RESPONSE = "Whatever."

  def hey(message)

    def message.yelling?
      self == self.upcase
    end

    def message.question?
      self.end_with?("?")
    end

    def message.nothing?
      self.nil? || self.strip.length == 0 
    end

    if message.nothing?
      NOTHING_RESPONSE
    elsif message.yelling?
      YELL_RESPONSE
    elsif message.question?
      QUESTION_RESPONSE
    else
      DEFAULT_RESPONSE
    end
  end
end
