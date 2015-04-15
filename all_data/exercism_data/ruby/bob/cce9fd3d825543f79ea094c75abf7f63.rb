class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.tr(' ', '').empty?
    return 'Woah, chill out!' if msg.upcase == msg && msg.downcase != msg
    return 'Sure.' if msg =~ /\?\z/
    'Whatever.'
  end
end
