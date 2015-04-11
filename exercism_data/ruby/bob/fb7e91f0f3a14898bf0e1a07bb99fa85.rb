class Bob

  def hey(message)
    response_tone = Response.new(message)
    case
    when response_tone.silent? then "Fine. Be that way."
    when response_tone.inquiring? then "Sure."
    when response_tone.shouting? then "Woah, chill out!"
    else "Whatever."
    end
  end

end

class Response

  attr_reader :message

  def initialize(message)
    @message = message
  end

  def shouting?
    message.upcase == message
  end

  def inquiring?
    message.end_with?("?")
  end

  def silent?
    message.to_s.nil?
  end

end
