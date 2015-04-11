class Bob
  RESPONSES = {shouting: 'Woah, chill out!', question: 'Sure.', nothing: 'Fine. Be that way!'}

  def hey(msg)
    return RESPONSES[:shouting] if shouting?(msg) 
    return RESPONSES[:question] if question?(msg) 
    return RESPONSES[:nothing]  if nothing?(msg) 
    'Whatever.'
  end

  def shouting?(msg)
    msg == msg.upcase && msg.match(/[a-zA-Z]/)
  end
  
  def question?(msg)
    msg.split.join(' ').match(/\?$/)
  end

  def nothing?(msg)
    msg.split.join('').empty?
  end
end
