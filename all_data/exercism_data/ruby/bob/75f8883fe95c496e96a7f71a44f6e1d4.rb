class Bob
  
  def hey(input)
		 
	silence =  input.nil? || input.strip.empty?
	shouting = !silence && input == input.upcase
    question = input =~ /\?$/

    (silence  && 'Fine. Be that way!') ||
    (shouting && 'Woah, chill out!') ||
    (question && 'Sure.') ||
    'Whatever.'

  end

end
