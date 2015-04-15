class Bob

  ANSWER_TO_NOTHING   = 'Fine. Be that way.'
  ANSWER_TO_YELL      = 'Woah, chill out!'
  ANSWER_TO_QUESTIONS = 'Sure.'
  REGULAR_ANSWER      = 'Whatever.'

  def hey(message = nil)
    case message
    when '', nil
      ANSWER_TO_NOTHING
    when message.upcase
      ANSWER_TO_YELL
    when /\?$/
      ANSWER_TO_QUESTIONS
    else
      REGULAR_ANSWER
    end
  end
end
