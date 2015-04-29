class Bob
  DEFAULT = "Whatever."
  MESSAGES = {
    :question? => "Sure.",
    :shouting? => "Woah, chill out!",
    :silence?  => "Fine. Be that way!"
  }

  PREDICATES = [
    :silence?, :shouting?, :question?
  ]

  def hey(message)
    message = message.to_s  

    MESSAGES.fetch(message_type(message), DEFAULT)
  end

  private
  def message_type(message)
    PREDICATES.detect {|sym| send(sym, message) }
  end

  def silence?(message)
    message.empty?
  end

  def shouting?(message)
    !silence?(message) && (message.upcase == message)
  end

  def question?(message)
    !shouting?(message) && (message[-1] == "?")
  end
end
