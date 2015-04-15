class Bob

  def hey(msg)
    if shouting?(msg)
      'Woah, chill out!'
    elsif questioning?(msg)
      'Sure.'
    elsif silence?(msg)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def shouting?(msg)
    msg.upcase == msg && msg =~ /[A-Z]/
  end

  def questioning?(msg)
    msg.chomp('?') != msg
  end

  def silence?(msg)
    msg.gsub(/\s/,'') == ''
  end
end
