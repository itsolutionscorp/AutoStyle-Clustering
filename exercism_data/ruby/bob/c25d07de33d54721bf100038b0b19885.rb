class Bob

  def hey convo
   return 'Sure.'              if question? convo
   return 'Fine. Be that way.' if silence? convo
   return 'Woah, chill out!'   if shouting? convo
   return 'Whatever.'          if commenting? convo
  end

  def shouting? convo
    convo == convo.upcase
  end

  def question? convo
    convo.end_with? "?"
  end

  def silence? convo
    convo.empty?
  end

  def commenting? convo
    convo
  end
end
