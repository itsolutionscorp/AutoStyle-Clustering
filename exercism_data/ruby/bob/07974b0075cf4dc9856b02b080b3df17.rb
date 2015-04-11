class Bob
  def hey(msg)
    if shouting?(msg)
      'Woah, chill out!'
    elsif asking_a_question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end

private
  def shouting?(msg)
    msg == msg.upcase
  end

  def asking_a_question?(msg)
    /[?]$/.match(msg)
  end
end
