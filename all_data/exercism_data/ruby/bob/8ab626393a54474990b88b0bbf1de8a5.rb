class Bob

  def hey(msg)
    if msg.strip.empty?
      'Fine. Be that way!'
    elsif msg =~ /[A-Z]/ && msg.upcase == msg
      'Woah, chill out!'
    elsif msg.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

end
