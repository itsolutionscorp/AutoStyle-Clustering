class Bob
  
  def hey (convo)
    convo = convo.strip
    return "Fine. Be that way!" if convo.length == 0
    return "Woah, chill out!" if (convo == convo.upcase && convo.upcase.match(/[A-Z]/))
    return "Sure." if convo[-1,1] == '?'
    "Whatever."
  end

end
