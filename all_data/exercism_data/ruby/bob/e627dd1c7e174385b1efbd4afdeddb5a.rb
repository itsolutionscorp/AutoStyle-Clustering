class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.match(/\A\s*\Z/) # No message
    return 'Woah, chill out!' if shouting?(msg)
    return 'Sure.' if msg[-1] == '?' # Question
    'Whatever.' # Everything else
  end

  private

  def shouting?(msg)
    msg.upcase!.nil? && msg.match(/[A-Z]/)
  end
end
