class Bob

  def initialize
  	@answer = { :shouting? => 'Woah, chill out!', :asking? => 'Sure.', :empty_message? => 'Fine. Be that way!' }
  	@answer.default = 'Whatever.'
  end

  def hey(message)
  	@message = MessageHandler.new(message)
  	answer	
  end

  def answer  	
  	@answer[@message.type_of_message]
  end 

end

class MessageHandler

  def initialize(message)
  	@message = message
  end

  def type_of_message
	[:shouting?, :asking?, :empty_message?].find {|type| send(type)}
  end

  private
  
  def shouting?
  	if @message[/[a-zA-Z]/]
  		@message == @message.upcase
  	end
  end

  def asking?
  	@message.end_with?('?')
  end

  def empty_message?
  	@message.gsub(" ","").empty?
  end

end
