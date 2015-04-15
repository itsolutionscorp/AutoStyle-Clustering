class Bob
  def hey(msg)
    if (msg.strip == '')
      'Fine. Be that way!'
    elsif (/[A-Za-z]/.match(msg) && msg.upcase == msg) # yelling at you
      'Woah, chill out!'
    elsif (msg[-1, 1] == '?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
