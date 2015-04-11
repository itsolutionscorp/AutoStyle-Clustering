class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.strip.empty?
    return 'Woah, chill out!' if msg == msg.upcase && msg != msg.downcase
    return 'Sure.' if msg.end_with?("?")
    'Whatever.'
  end
end
