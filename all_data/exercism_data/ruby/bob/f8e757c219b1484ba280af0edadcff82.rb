#Notes: When I independently run this file with the tests in bob_test.rb, I get the right answers, but when I run bob_test.rb proper, the Actual results are all 'nil'. My thought is that there's something wrong with either my initialize or Teenager Test's setup, but I'm just not sure what the issue is. Any feedback would be appreciated!

class Bob

  def initialize
  end
	
  def hey(call)
	if self.silenttreatment?(call)
		puts "Fine. Be that way!"
	elsif self.isyelling?(call)
		puts "Woah, chill out!"
	elsif self.boringtalk?(call)
		puts "Whatever."
	elsif self.askingsomething?(call)
		puts "Sure."
	else
		puts "I ain't got time for this."
	end
  end

    def isyelling?(call)
	if call.eql?(call.upcase)
	  true
	else
	  false
	end
  end
  
  def silenttreatment?(call)
    if call.nil? || (call.split(" ").count === 0)
	  true
	else
	  false
	end
  end
  
  def boringtalk?(call)
	  if call.end_with?("\.") || call.end_with?("\!")
		true
	  else
		false
	  end
  end
  
  def askingsomething?(call)
    if call.end_with?("\?")
      true
	else
	  false
    end
  end
end

 

#Test 1: If you ask him a question, he says "Sure."
#Test 2: If you tell him something, he says "Whatever."
#Test 3: If you yell in all caps, he says "Woah, chill out!"
#Test 4: If you call hey but don't say anything, he says "Fine. Be that way!"
