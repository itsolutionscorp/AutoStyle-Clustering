class Bob
  RESPONSES = { silence: "Fine. Be that way!", shouting: "Woah, chill out!", question: "Sure.", statement: "Whatever." }

  def hey(message)
    case message
    when nil
      respond_to :silence
    when ""
      respond_to :silence
    when message.upcase
      respond_to :shouting
    when /\?$/
      respond_to :question
    else
      respond_to :statement
    end
  end

  def respond_to(what = :statement)
    RESPONSES[what] || RESPONSES[:statement]
  end
end
