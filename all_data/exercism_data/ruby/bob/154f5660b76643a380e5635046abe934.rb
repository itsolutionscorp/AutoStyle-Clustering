#Notes: My thanks to graysonwright for his notes on my first submission. This version passes the tests and is substantially nicer to look at than my first attempt at the exercise.

class Bob
	
  def hey(call)
	if silent_treatment?(call)
		"Fine. Be that way!"
	elsif is_yelling?(call)
		"Woah, chill out!"
	elsif boring_talk?(call)
		"Whatever."
	elsif asking_something?(call)
		"Sure."
	else
		"I ain't got time for this."
	end
  end

  def is_yelling?(call)
	call.eql?(call.upcase)
  end
  
  def silent_treatment?(call)
    call.nil? || call.strip.empty?
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
