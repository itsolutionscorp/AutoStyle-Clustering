class Bob

  def hey(msg)
    if nothing?(msg)
      'Fine. Be that way!'
    elsif yelling?(msg)
      'Woah, chill out!'
    elsif question?(msg)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def nothing?(msg)
    msg.strip.empty?
  end

  def yelling?(msg)
    msg =~ /[A-Z]/ && msg.upcase == msg
  end

  def question?(msg)
    msg.end_with? '?'
  end

end
