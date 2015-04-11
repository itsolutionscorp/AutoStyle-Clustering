class Bob
  REPLIES = {
    silence: "Fine. Be that way.",
    shouting: "Woah, chill out!",
    question: "Sure.",
    default: "Whatever."
  }

  def hey message
    msg = make_replyable message

    if silent? msg
      reply :silence
    elsif shouting? msg
      reply :shouting
    elsif asking_question? msg
      reply :question
    else
      reply :default
    end
  end

  private

  def make_replyable msg
    msg.respond_to?(:to_str) ? msg.to_str : ''
  end

  def reply msg_type
    REPLIES[msg_type]
  end

  def silent? msg
    msg.empty?
  end

  def shouting? msg
    msg == msg.upcase
  end

  def asking_question? msg
    msg.end_with? '?'
  end
end
