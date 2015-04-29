class Bob

  def hey(msg)
    case type_of(msg.to_s)
    when 'yelling'  then 'Woah, chill out!'
    when 'question' then 'Sure.'
    when 'silence'  then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  def type_of(msg)
    return 'silence'  if msg.strip.empty?
    return 'yelling'  if msg.upcase == msg
    return 'question' if msg.end_with? '?'
  end

end
