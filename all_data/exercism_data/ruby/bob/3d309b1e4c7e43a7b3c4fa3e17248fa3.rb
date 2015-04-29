class Bob
  def hey(msg)
    result = nil
    if msg.nil? || msg.empty?
      result = 'Fine. Be that way!'
    elsif msg.upcase == msg
      result = 'Woah, chill out!'
    elsif msg.end_with? '?'
      result = 'Sure.'
    else
      result = 'Whatever.'
    end
    result
  end
end
