class Bob
  SAID_NOTHING_REPLY = 'Fine. Be that way!'
  YELLING_REPLY = 'Woah, chill out!'
  QUESTION_REPLY = 'Sure.'
  DEFAULT_REPLY = 'Whatever.'
  QUESTION_MARK = '?'

  def hey (message)
    if said_nothing? message
      SAID_NOTHING_REPLY
    elsif yelling? message
      YELLING_REPLY
    elsif question? message
      QUESTION_REPLY
    else
      DEFAULT_REPLY
    end
  end

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with? QUESTION_MARK
  end

  def said_nothing?(message)
    message.to_s.strip.empty?
  end
end
