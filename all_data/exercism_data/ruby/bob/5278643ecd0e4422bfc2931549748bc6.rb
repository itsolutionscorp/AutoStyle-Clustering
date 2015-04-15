class Bob
  def hey(conversation, stripped = conversation.strip)
    return 'Fine. Be that way!' if stripped.empty?
    return 'Woah, chill out!' if stripped.upcase == conversation
    return 'Sure.' if stripped.end_with? '?'
    'Whatever.'
  end
end
