class Bob
  FINE_BE_THAT_WAY = 'Fine. Be that way!'
  WOAH_CHILL_OUT = 'Woah, chill out!'
  SURE = 'Sure.'
  WHATEVER = 'Whatever.'
  QUESTION_MARK = '?'

  def hey (message)
    if value? message
      FINE_BE_THAT_WAY
    elsif upper_case? message
      WOAH_CHILL_OUT
    elsif question? message
      SURE
    else
      WHATEVER
    end
  end

  def upper_case?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with? QUESTION_MARK
  end

  def value?(message)
    message.to_s.empty? || message.strip.length < 1
  end
end
