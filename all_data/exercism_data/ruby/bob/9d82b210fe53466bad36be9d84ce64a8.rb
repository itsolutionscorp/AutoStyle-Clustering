class Bob
  def hey(saying)
  	if silent?(saying)
  		return 'Fine. Be that way!'
  	elsif saying.upcase == saying
  		return 'Woah, chill out!'
  	elsif question?(saying)
  		return 'Sure.'
  	end
  	'Whatever.'
  end

  def silent?(s)
  	if s.nil? || s.empty?
  		return true
  	end
  end

  def question?(s)
  	if s[-1,1] == '?'
  		return true
  	end
  end

end
