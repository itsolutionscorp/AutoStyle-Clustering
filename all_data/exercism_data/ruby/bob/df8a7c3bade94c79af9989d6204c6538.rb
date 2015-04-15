class Bob
  def hey(msg)
    msg.delete!("\n")
    response_to_message(msg)
  end

  def response_to_message(msg)
    if is_yelling?(msg) && has_letters?(msg)
      'Woah, chill out!'
    elsif is_a_question?(msg)
      'Sure.'
    elsif is_nothing?(msg)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def is_yelling?(msg)
    msg == msg.upcase
  end

  def is_a_question?(msg)
    /\?$/.match(msg) ? true : false
  end

  def has_letters?(msg)
    /[a-zA-Z]/.match(msg) ? true : false
  end

  def is_nothing?(msg)
    msg.empty? || /\s+$/.match(msg)
  end
end
