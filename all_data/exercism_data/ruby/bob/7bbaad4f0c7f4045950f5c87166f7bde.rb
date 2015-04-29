class Bob
  
  def hey(stating_something)
	
	if stating_something.to_s.strip == ""
	  "Fine. Be that way!"
	elsif stating_something == stating_something.upcase 
	  "Woah, chill out!"
	elsif stating_something.end_with?("?")
	  "Sure."
	else
	  "Whatever."
	end
	
  end

end
