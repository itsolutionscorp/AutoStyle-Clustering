class Bob
  def hey(msg)
    if msg.upcase == msg and msg =~  /[A-Z]/
      'Woah, chill out!'
    elsif msg[-1] == '?'
      'Sure.'
    elsif /\A\s*\Z/.match(msg)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
