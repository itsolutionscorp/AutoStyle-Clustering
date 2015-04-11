class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.strip.empty?
    return 'Woah, chill out!' if shouting?(msg)
    return 'Sure.' if msg.end_with? '?'
    'Whatever.' # Everything else
  end

  private

  def shouting?(msg)
    msg.upcase!.nil? && msg.match(/[A-Z]/)
  end
end
