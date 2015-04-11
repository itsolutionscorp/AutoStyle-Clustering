class Bob
  
  def hey(msg)
    if is_yelling?(msg)
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
    msg == msg.upcase && has_letters?(msg)
  end

  def is_a_question?(msg)
    /\?\z/.match(msg)
  end

  def has_letters?(msg)
    /[a-zA-Z]/.match(msg)
  end

  def is_nothing?(msg)
    msg.empty? || /\s+\z/.match(msg)
  end
end
