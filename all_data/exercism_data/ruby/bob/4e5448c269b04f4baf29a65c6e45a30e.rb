class Bob

  def hey text
    return 'Fine. Be that way!' if text.strip.empty?
    return 'Woah, chill out!'   if text.upcase == text
    return 'Sure.'              if text.end_with? '?'
    return 'Whatever.'
  end
end
