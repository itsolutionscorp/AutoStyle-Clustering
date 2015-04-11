class Bob
  def hey(msg)
    msg = msg.to_s

    return 'Fine. Be that way!' if msg.empty?
    return 'Woah, chill out!'   if msg.upcase == msg
    return 'Sure.'              if msg.end_with? '?'

    'Whatever.'
  end
end
