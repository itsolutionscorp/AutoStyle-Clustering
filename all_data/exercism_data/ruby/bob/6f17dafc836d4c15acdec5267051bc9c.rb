class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.strip == ''
    return 'Woah, chill out!' if msg == msg.upcase && msg =~ /[A-Z]+/
    return 'Sure.' if msg[-1] == '?'
    'Whatever.'
  end
end
