class Bob

  def hey(message)
    respond(message.strip)
  end

  private

  def respond(message)
    technique = CONVERSATION_TECHNIQUES.find do |technique|
      technique[:message_type].match(message)
    end
    technique[:response]
  end

  MULTILINE = /\n/
  SHOUT = /^[A-Z0-9\W ]+(\?|!)?$/
  QUESTION = /\?$/
  EMPTY = /^$/
  ANYTHING = //

  CONVERSATION_TECHNIQUES = [
    { :message_type => MULTILINE, :response => "Whatever." },
    { :message_type => EMPTY, :response => "Fine. Be that way!" },
    { :message_type => SHOUT, :response => "Woah, chill out!" },
    { :message_type => QUESTION, :response => "Sure." },
    { :message_type => ANYTHING, :response => "Whatever." },
  ]
end
