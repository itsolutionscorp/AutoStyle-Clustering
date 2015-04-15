class Bob

  def hey(convo)

   return 'Sure.'              if convo.end_with? "?"
   return 'Fine. Be that way.' if convo.empty?
   return 'Woah, chill out!'   if is_upcase?(convo)
   return 'Whatever.'          if convo
  end

  def is_upcase?(convo)
    convo == convo.upcase
  end
end
