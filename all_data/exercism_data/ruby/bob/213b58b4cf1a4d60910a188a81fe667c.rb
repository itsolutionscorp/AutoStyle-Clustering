class Bob

  def hey(msg)
    if silence?(msg)
      'Fine. Be that way!'
    elsif anger?(msg)
      'Woah, chill out!'
    elsif question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(msg)
    msg.strip.empty?
  end

  def anger?(msg)
    msg == msg.upcase
  end

  def question?(msg)
    msg.end_with?('?')
  end
end
