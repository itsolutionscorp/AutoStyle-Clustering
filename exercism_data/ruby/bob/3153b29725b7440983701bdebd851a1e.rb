class Bob
  FINE_BE_THAT_WAY = 'Fine. Be that way!'
  WOAH_CHILL_OUT = 'Woah, chill out!'
  SURE = 'Sure.'
  WHATEVER = 'Whatever.'
  QUESTION_MARK = '?'

  def hey (message)
    if said_nothing? message
      FINE_BE_THAT_WAY
    elsif yelling? message
      WOAH_CHILL_OUT
    elsif question? message
      SURE
    else
      WHATEVER
    end
  end

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with? QUESTION_MARK
  end

  def said_nothing?(message)
    message.to_s.empty? || message.strip.length < 1
  end
end
