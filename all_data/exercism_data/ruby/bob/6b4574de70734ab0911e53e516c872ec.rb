class Bob
  def hey(msg)
    return fine if empty?(msg)
    return chill_out if forcefully_question?(msg)
    return chill_out if forcefully_talking?(msg)
    return sure if question?(msg)
    return whatever
  end

  private

  def forcefully_question?(msg)
    return true if upcase?(msg) and question?(msg)
    return false
  end

  def forcefully_talking?(msg)
    return true if upcase?(msg) and shouting?(msg)
    return true if upcase?(msg)
    return false
  end

  def empty?(msg)
    msg.strip.empty?
  end

  def upcase?(msg)
    msg == msg.upcase
  end

  def question?(msg)
    msg.end_with?("?")
  end
  
  def shouting?(msg)
    msg.end_with?("!")
  end

  def whatever
    'Whatever.'
  end

  def sure
    'Sure.'
  end

  def chill_out
    'Woah, chill out!'
  end

  def fine
    "Fine. Be that way!"
  end
end
