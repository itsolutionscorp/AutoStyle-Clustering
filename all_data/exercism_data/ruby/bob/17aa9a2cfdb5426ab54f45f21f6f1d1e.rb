#Notes: Thanks again to Graysonwright and crazymyki. As it stands, the boring_talk method ("Whatever") doesn't get used, but I'm leaving it as part of the class for the time being.

class Bob
	
  def hey(call)
	if silent_treatment?(call)
		"Fine. Be that way!"
	elsif is_yelling?(call)
		"Woah, chill out!"
	elsif asking_something?(call)
		"Sure."
	else
		"Whatever."
	end
  end

  def is_yelling?(call)
	call.eql?(call.upcase)
  end
  
  def silent_treatment?(call)
    call.to_s.strip.empty?
  end
  
  def boring_talk?(call)
	call.end_with?(".") || call.end_with?("!")
  end
  
  def asking_something?(call)
    call.end_with?("?")
  end
end

 

#Test 1: If you ask him a question, he says "Sure."
#Test 2: If you tell him something, he says "Whatever."
#Test 3: If you yell in all caps, he says "Woah, chill out!"
#Test 4: If you call hey but don't say anything, he says "Fine. Be that way!"
