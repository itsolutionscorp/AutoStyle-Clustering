class Bob
  def hey(msg)
    result = nil
    if msg.nil? || msg.empty?
      'Fine. Be that way!'
    elsif msg.upcase == msg
      'Woah, chill out!'
    elsif msg.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
