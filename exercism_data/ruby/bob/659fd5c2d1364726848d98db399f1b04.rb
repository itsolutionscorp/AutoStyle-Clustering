class Bob
  def hey(msg)
    if msg.to_s.empty?
      'Fine. Be that way.'
    elsif msg == msg.upcase
      'Woah, chill out!'
    elsif msg[-1] == ('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end