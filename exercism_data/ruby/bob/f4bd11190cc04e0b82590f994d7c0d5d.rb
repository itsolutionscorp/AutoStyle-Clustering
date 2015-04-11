class Bob
  FINE_BE_THAT_WAY = 'Fine. Be that way!'
  WOAH_CHILL_OUT = 'Woah, chill out!'
  SURE = 'Sure.'
  WHATEVER = 'Whatever.'
  QUESTION_MARK = '?'

  def hey (param1)
    if has_value? param1
      FINE_BE_THAT_WAY
    elsif is_upper_case? param1
      WOAH_CHILL_OUT
    elsif is_a_question param1
      SURE
    else
      WHATEVER
    end
  end

  def is_upper_case?(param1)
    param1.match(/\p{Lower}/).to_s.length < 1
  end

  def is_a_question(param1)
    param1[-1,1] == QUESTION_MARK
  end

  def has_value?(param1)
    param1.nil? || param1.strip.length < 1
  end
end
