class Bob

  def hey(msg)
    case
      when silent?(msg)
        'Fine. Be that way.'
      when shouting?(msg)
        'Woah, chill out!'
      when question?(msg)
        'Sure.'
      else
        'Whatever.'
    end
  end

  private

  def silent?(msg)
    msg.nil? || msg.empty?
  end

  def shouting?(msg)
    msg == msg.upcase
  end

  def question?(msg)
    msg.end_with?("?")
  end

end
