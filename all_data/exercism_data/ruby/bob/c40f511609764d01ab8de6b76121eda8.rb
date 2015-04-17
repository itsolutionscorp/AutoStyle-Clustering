class Bob

  def hey(msg)
    if msg.strip == ''
      'Fine. Be that way!'
    elsif msg == msg.upcase
      'Woah, chill out!'
    elsif msg.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

end