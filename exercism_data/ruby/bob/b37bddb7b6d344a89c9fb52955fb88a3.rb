class Bob

  def initialize
    @teenage_responces = {stating: 'Whatever.',questioned: 'Sure.', shouted: 'Woah, chill out!', silence: 'Fine. Be that way!'}
  end
  
  def hey(drivel)
    self.check_sentence(drivel)
	@teen_speak or @teenage_responces[:stating].to_s 
  end	
  
  def check_sentence(drivel)
    if drivel.empty? or drivel.match /^\s*$/ 
	  self.answer(@teenage_responces[:silence]) unless drivel.include?("\n")
    elsif drivel == drivel.upcase
	  self.answer(@teenage_responces[:shouted])
	elsif drivel[drivel.length-1] == "?"
	  self.answer(@teenage_responces[:questioned])
	end
  end
  
  def answer(*teen_speak)
    @teen_speak = teen_speak[0]
  end
	
end
