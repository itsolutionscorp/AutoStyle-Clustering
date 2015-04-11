class Bob

  def hey(msg)
    case
      when is_silent?(msg)
        return 'Fine. Be that way.'
      when is_shouting?(msg)
        return 'Woah, chill out!'
      when is_a_question?(msg)
        return 'Sure.'
    end
    return 'Whatever.'
  end

  private

  def is_silent?(msg)
     msg.nil? || msg.empty?
  end

  def is_shouting?(msg)
    msg == msg.upcase
  end

  def is_a_question?(msg)
    msg.split("").last == "?"
  end

end
