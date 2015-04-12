class Bob
  RESPONSES = {yell: 'Woah, chill out!',
               question: 'Sure.',
               nothing: 'Fine. Be that way!',
               anything: 'Whatever.'}
  def hey(msg)
    if nothing?(msg)
      RESPONSES[:nothing]
    elsif yelling?(msg)
      RESPONSES[:yell]
    elsif question?(msg)
      RESPONSES[:question]
    else
      RESPONSES[:anything]
    end
  end
  def question?(msg)
    msg.end_with?('?')
  end
  def yelling?(msg)
    tmp = msg.gsub(/[^a-zA-Z]/,'')
    tmp != "" and tmp == tmp.upcase
  end
  def nothing?(msg)
    msg.rstrip == "" 
  end
  private :question?, :yelling?, :nothing?
end