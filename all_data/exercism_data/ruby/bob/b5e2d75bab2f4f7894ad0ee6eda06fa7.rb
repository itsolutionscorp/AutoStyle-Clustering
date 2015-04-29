class Bob
  def hey(msg)
    result = nil
    if msg.upcase == msg
      result = 'Woah, chill out!'
    elsif msg.include? '?'
      result = 'Sure.'
    else
      result = 'Whatever.'
    end
    result
  end
end
