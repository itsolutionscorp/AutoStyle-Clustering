class Bob
  def hey(msg)
    if msg.to_s.empty?
      'Fine. Be that way.'
    elsif upcase?(msg)
      'Woah, chill out!'
    elsif question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?(msg)
    msg[-1] == '?'
  end

  def upcase?(msg)
    msg == msg.upcase
  end
end
