class Bob
  RESPONSES = { silence: "Fine. Be that way!", shouting: "Woah, chill out!", question: "Sure.", statement: "Whatever." }

  def hey(message)
    respond_to(message_type(message))
  end

  def message_type(message)
    if !message || message == ""
      :silence
    elsif message == message.upcase
      :shouting
    elsif message[-1] == "?"
      :question
    else
      :statement
    end
  end

  def respond_to(what = :statement)
    RESPONSES[what] || RESPONSES[:statement]
  end
end
