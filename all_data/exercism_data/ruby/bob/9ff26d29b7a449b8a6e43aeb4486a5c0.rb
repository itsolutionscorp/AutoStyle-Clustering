class Bob
  Response = {no_message: "Fine. Be that way!", shouting: "Woah, chill out!", question: "Sure.", statement: "Whatever."}
  def hey(message)
    Response[response_for(message)]
  end

  def response_for(message)
    return :no_message if not_given?(message)
    return :shouting   if shouting?(message)
    return :question   if questioning?(message)
    return :statement
  end

  def shouting?(message)
    message.delete('^[a-zA-Z]').match(/^[A-Z]+$/)
  end

  def questioning?(message)
    message[-1] == "?"
  end

  def not_given?(message)
    message.respond_to?(:empty?) ? message.empty? : !message
  end
end
