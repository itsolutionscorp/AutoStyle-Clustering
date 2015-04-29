class Bob

  def hey(comment)  
	response = 'Whatever.'
    case
		when relax(comment)
		  response = 'Fine. Be that way!'
		when yelling(comment)
		  response = 'Woah, chill out!'
		when question(comment)
		  response = 'Sure.'		
    end	
	return response	
  end

  def yelling(comment)
    comment =~ /[A-Z]/ and comment.upcase == comment
  end
  
  def relax(comment)
    comment.strip.empty?
  end

  def question(comment)
    comment.end_with?('?')
  end

end
