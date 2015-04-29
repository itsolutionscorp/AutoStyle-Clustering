class Bob

  def initialize
    @teenage_responces = {stating: 'Whatever.',
                          questioned: 'Sure.', 
                          shouted: 'Woah, chill out!', 
                          silence: 'Fine. Be that way!'}
  end

  def hey(drivel)
    check_sentence(drivel)
    @teen_speak or @teenage_responces[:stating]
  end   

  def check_sentence(drivel)
    if drivel.empty? or drivel.match /^\s*$/ 
      answer(@teenage_responces[:silence]) unless drivel.include?("\n")
    elsif drivel == drivel.upcase
      answer(@teenage_responces[:shouted])
    elsif drivel[drivel.length-1] == "?"
      answer(@teenage_responces[:questioned])
    end
  end

  def answer(teen_speak)
    @teen_speak = teen_speak
  end
end
