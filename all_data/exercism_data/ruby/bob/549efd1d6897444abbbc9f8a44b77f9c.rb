class Bob
  def hey(msg)
    if msg.scan(/[a-zA-Z]+/) == msg.scan(/[A-Z]+/) && !msg.scan(/[A-Z]+/).empty?
      return 'Woah, chill out!'
    elsif msg[-1] == '?'
      return 'Sure.'
    elsif msg.split.empty?
      return 'Fine. Be that way!'
    else
      return'Whatever.'
    end
  end
end
