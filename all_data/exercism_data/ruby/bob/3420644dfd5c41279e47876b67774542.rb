class Bob
  def hey(message)
    Response.for(message)
  end
end

class Response
  def self.for(message)
    new(message).response
  end

  def initialize(message)
    @message = message
  end

  def response
    case
    when silence?
      "Fine. Be that way!"
    when shouting?
      "Woah, chill out!"
    when questioning?
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?
    @message.strip.empty?
  end

  def shouting?
    @message.upcase == @message
  end

  def questioning?
    @message.end_with?("?")
  end
end
