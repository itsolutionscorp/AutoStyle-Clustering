class Bob
  ELSE_MSG = 'Whatever.'
  SHOUT_MSG = 'Woah, chill out!'
  QUESTION_MSG = 'Sure.'
  SILENCE_MSG = 'Fine. Be that way!'

  def hey(msg)
    msg = msg.strip
    if all_caps_letters(msg)
      SHOUT_MSG
    elsif ending_q_mark(msg)
      QUESTION_MSG
    elsif msg == ''
      SILENCE_MSG
    else
      ELSE_MSG
    end
  end

  def all_caps_letters(msg)
    msg.upcase == msg && msg.upcase != msg.downcase
  end

  def ending_q_mark(msg)
    msg.split('').last == '?'
  end
end
