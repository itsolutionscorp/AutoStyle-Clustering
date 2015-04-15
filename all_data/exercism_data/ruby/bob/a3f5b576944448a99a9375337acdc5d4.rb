class Bob

  def hey(convo)
    is_upcase = convo == convo.upcase

   'Sure.' if convo.end_with? "?"
   'Woah, chill out!' if is_upcase
   'Fine, be that way.' if convo.empty?
   'Whatever.' if convo
  end
end
