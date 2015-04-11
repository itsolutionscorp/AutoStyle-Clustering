class Bob

  def hey(msg)

    if msg =~ /\A\s*\Z/
      'Fine. Be that way!'
    elsif msg.upcase == msg
      'Woah, chill out!'
    elsif msg =~ /\?\Z/
      'Sure.'
    else
      'Whatever.'
    end
  end

end
