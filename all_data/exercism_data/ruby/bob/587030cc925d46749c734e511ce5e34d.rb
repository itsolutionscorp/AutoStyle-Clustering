class Bob
  def hey(chat)
    return 'Fine. Be that way!' if chat.strip.empty?
    return 'Woah, chill out!'   if !chat.dup.upcase!
    return 'Sure.'              if chat.end_with? '?'
    'Whatever.'
  end
end
