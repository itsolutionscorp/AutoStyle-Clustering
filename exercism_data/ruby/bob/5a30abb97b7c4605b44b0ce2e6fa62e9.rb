class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.match(/\A\s*\Z/)
    return 'Woah, chill out!' if msg.upcase!.nil? && msg.match(/[A-Z]/)
    return 'Sure.' if msg[-1] == '?'
    'Whatever.'
  end
end
