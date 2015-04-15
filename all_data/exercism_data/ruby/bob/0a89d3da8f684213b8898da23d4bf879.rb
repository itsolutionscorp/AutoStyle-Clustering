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

 
#teenager = Bob.new

#teenager.hey(nil)
#teenager.hey("Tom-ay-to, tom-aaaah-to.")
#teenager.hey("WATCH OUT!")
#teenager.hey("Does this cryogenic chamber make me look fat?")
#teenager.hey('You are, what, like 15?')
#teenager.hey("Let's go make out behind the gym!")
#teenager.hey("It's OK if you don't want to go to the DMV.")
#teenager.hey("WHAT THE HELL WERE YOU THINKING?")
#teenager.hey("1, 2, 3 GO!")
#teenager.hey('ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!')
#teenager.hey('I HATE YOU')
#teenager.hey('Ending with ? means a question.')
#teenager.hey("Wait! Hang on. Are you going to be OK?")
#teenager.hey('')
#teenager.hey('   ')

#Test 1: If you ask him a question, he says "Sure."
#Test 2: If you tell him something, he says "Whatever."
#Test 3: If you yell in all caps, he says "Woah, chill out!"
#Test 4: If you call hey but don't say anything, he says "Fine. Be that way!"
