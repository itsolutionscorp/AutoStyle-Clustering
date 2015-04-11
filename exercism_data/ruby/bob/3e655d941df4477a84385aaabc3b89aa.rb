class Bob

  def hey(input)
    reply_to(Communication.new(input))
  end

  def reply_to(communication)
    case
    when communication.silence?
      "Fine. Be that way!"
    when communication.shouting?
      "Woah, chill out!"
    when communication.questioning?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Communication
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def silence?
    input.strip == ""
  end

  def shouting?
    input =~ /[a-zA-Z]/ && input.upcase == input
  end

  def questioning?
    input.end_with?("?")
  end
end
