class Bob

  RESPONSE = {
    default: 'Whatever.',
    agree:   'Sure.',
    shout:   'Woah, chill out!',
    silent:  'Fine. Be that way.'
  }

  def hey(msg)
    msg = msg.to_s
    return RESPONSE[:silent] if msg.empty?
    return RESPONSE[:shout]  if msg.upcase == msg
    return RESPONSE[:agree]  if msg.end_with? '?'
    RESPONSE[:default]
  end
end
