class Bob
  class Speech
    def initialize(message)
      @message = message
    end

    def nothing?
      @message.strip.empty?
    end

    def question?
      @message.end_with?("?")
    end

    def yell?
      @message.upcase == @message
    end
  end

  def hey(message)

    speech = Speech.new message

    if speech.nothing?
      "Fine. Be that way!"
    elsif speech.yell?
      "Woah, chill out!"
    elsif speech.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
