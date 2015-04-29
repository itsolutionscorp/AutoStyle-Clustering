class Bob
  
  def hey(stating_something)
	
	if stating_something.to_s.strip == ""
	  response = "Fine. Be that way!"
	elsif stating_something == stating_something.upcase 
	  response = "Woah, chill out!"
	elsif stating_something[-1,1] == "?"
	  response = "Sure."
	elsif
	  response = "Whatever."
	end

	return response
	
  end

end
