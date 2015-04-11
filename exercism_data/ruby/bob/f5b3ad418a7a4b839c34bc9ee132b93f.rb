class Bob

  def initialize()
		
  end	

  def hey(question)  
	response = 'Whatever.'
    case
		when relax(question)
		  response = 'Fine. Be that way!'
		when yelling(question)
		  response = 'Woah, chill out!'
		when question(question)
		  response = 'Sure.'		
    end	
	return response	
  end

  def yelling(s)
    s =~ /[A-Z]/ and s.upcase == s
  end
  
  def relax(s)
    s.strip.empty?
  end

  def question(s)
    s.end_with?('?')
  end

end
