class Bob
  def hey(chat)
    return 'Fine. Be that way!' if chat.strip.empty?
    return 'Woah, chill out!'   if !chat.upcase!
    return 'Sure.'              if chat.end_with? '?'
    'Whatever.'
  end
end
