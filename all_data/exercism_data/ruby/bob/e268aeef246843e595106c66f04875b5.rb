class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.strip.empty?
    return 'Woah, chill out!'   if msg.upcase == msg
    return 'Sure.'              if msg[-1] == '?'
    'Whatever.'
  end
end
