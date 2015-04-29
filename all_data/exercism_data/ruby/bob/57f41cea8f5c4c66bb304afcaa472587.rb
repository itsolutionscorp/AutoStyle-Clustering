class Bob
  ACCEPTED_ANSWERS = {
  	shouting?: 'Woah, chill out!',
  	asking?: 'Sure.',
  	empty_message?: 'Fine. Be that way!'
  }

  def hey(message)  	
  	answer(message)
  end

  private
  
  def answer(message)  	  	
  	ACCEPTED_ANSWERS[type_of_message?(message)] || 'Whatever.'
  end 

  def type_of_message?(message)
		ACCEPTED_ANSWERS.keys.find { |type| send(type, message) }
  end

  def shouting?(message)
  	message[/[A-Z]/] && message == message.upcase
  end

  def asking?(message)
  	message.end_with?('?')
  end

  def empty_message?(message)
  	message.strip.empty?
  end

end
