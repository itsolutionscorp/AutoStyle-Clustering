class Bob
  
  def silence?(x)
  	x.nil? || x.strip.empty?
  end

  def shouting?(x)
  	!silence?(x) && x == x.upcase
  end

  def question?(x)
  	x =~ /\?$/
  end

  def hey(input)
		 
    (silence?(input)  && 'Fine. Be that way!') ||
    (shouting?(input) && 'Woah, chill out!') ||
    (question?(input) && 'Sure.') ||
    'Whatever.'

  end

end
