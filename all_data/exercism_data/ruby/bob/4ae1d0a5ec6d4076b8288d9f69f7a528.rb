class Bob
  def hey(message)
    return case Message.new(message).type
    when :ignoring then "Fine. Be that way!"
    when :shouting then "Woah, chill out!"
    when :questioning then "Sure."
    else "Whatever."
    end
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def type
    if ignoring?
      return :ignoring
    elsif shouting?
      return :shouting
    elsif questioning?
      return :questioning
    else
      return :stating
    end
  end

  def questioning?
    message[-1] == "?"
  end

  def shouting?
    message == message.upcase
  end

  def ignoring?
    message.strip.empty?
  end
end
