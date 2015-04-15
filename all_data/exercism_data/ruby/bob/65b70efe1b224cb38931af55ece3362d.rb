class Responder
  def initialize(message)
    @message = message
  end

  attr_reader :message

  def response
    if none?
      "Fine. Be that way!"
    elsif yelling?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  def yelling?
    message.upcase == message
  end

  def question?
    message.end_with?("?")
  end

  def none?
    # where is active support when you need it! blank?
    message.nil? || message == ""
  end
end

class Bob
  def hey(message)
    Responder.new(message).response
  end
end
