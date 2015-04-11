class Bob
  def hey(msg)
    if upcase?(msg)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

private
  def upcase?(msg)
    msg == msg.upcase
  end
end
