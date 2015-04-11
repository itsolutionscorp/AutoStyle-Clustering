class Bob
  def hey(message)
    message = Salutation.new(message)

    if message.silence?
      "Fine. Be that way!"
    elsif message.shouting?
      "Woah, chill out!"
    elsif message.questioning?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  class Salutation
    attr_reader :message

    def initialize(message)
      @message = String(message).chomp
    end

    def silence?
      message.empty?
    end

    def questioning?
      message.end_with?("?")
    end

    def shouting?
      message.upcase == message
    end
  end
end
