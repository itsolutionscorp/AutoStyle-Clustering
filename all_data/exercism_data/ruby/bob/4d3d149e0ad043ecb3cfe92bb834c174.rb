class Bob
  def hey(msg)
    case msg.to_s
    when silence?
      silence_response
    when yelling?(msg)
      yelling_response
    when questioning?
      question_response
    else
      standard_response
    end
  end

  private

  def silence_response
    'Fine. Be that way!'
  end

  def yelling_response
    'Woah, chill out!'
  end

  def question_response
    'Sure.'
  end

  def standard_response
    'Whatever.'
  end

  def silence?
    /\A\s*\z/
  end

  def yelling?(msg)
    msg.upcase
  end

  def questioning?
    /\?\z/
  end
end
