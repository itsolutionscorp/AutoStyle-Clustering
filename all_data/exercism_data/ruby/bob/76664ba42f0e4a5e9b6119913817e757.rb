class Bob
  def hey(msg)
    safe_msg = kill_newlines(msg)

    if silence?(safe_msg)
      'Fine. Be that way!'
    elsif shouting?(safe_msg)
      'Woah, chill out!'
    elsif question?(safe_msg)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence? msg
    msg.match(/^\s*$/)
  end

  def shouting? msg
    msg == msg.upcase
  end

  def question? msg
    msg.end_with? '?'
  end

  def kill_newlines msg
    msg.gsub("\n", '')
  end
end
