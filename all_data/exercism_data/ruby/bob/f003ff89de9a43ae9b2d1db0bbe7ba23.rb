class Bob
  def hey(msg)
  	if without_greeting(msg)
  	  'Fine. Be that way!'
  	elsif yelling(msg)
  	  'Woah, chill out!'
  	elsif asking_a_question(msg)
  	  'Sure.'
  	else
  	  'Whatever.'
  	end
  end

  def without_greeting(msg)
  	msg.strip.empty?
  end

  def yelling(msg)
  	msg.upcase == msg
  end

  def asking_a_question(msg)
  	msg.end_with?('?')
  end
end 
