class Bob

  def hey(message)
  	@message = message
  	answer	
  end

  def answer
  	answer = [:shouting?, :asking?, :no_message?, :whatever].detect {|method| send(method)}
  	send(answer)
  end

  def shouting?
  	trimmed_message = @message.gsub(/[^a-zA-Z]/,'')
  	unless trimmed_message.empty?
  		'Woah, chill out!' if trimmed_message == trimmed_message.upcase
  	end
  end

  def asking?
  	'Sure.' if @message[/\?\z/]
  end

  def no_message?
  	'Fine. Be that way!' if @message.gsub(" ","").empty?
  end

  def whatever
  	'Whatever.'
  end

end
