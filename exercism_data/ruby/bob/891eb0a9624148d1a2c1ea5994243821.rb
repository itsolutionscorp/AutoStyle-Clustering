class Bob
  ELSE_RESPONSE     = 'Whatever.'
  SHOUT_RESPONSE    = 'Woah, chill out!'
  QUESTION_RESPONSE = 'Sure.'
  SILENCE_RESPONSE  = 'Fine. Be that way!'

  def hey(msg)
    msg = msg.strip
    if all_caps_letters?(msg)
      SHOUT_RESPONSE
    elsif ending_q_mark?(msg)
      QUESTION_RESPONSE
    elsif msg == ''
      SILENCE_RESPONSE
    else
      ELSE_RESPONSE
    end
  end

  def all_caps_letters?(msg)
    msg.upcase == msg && msg =~ /[A-Z]/
  end

  def ending_q_mark?(msg)
    msg.split('').last == '?'
  end
end
