class Bob

  attr_accessor :accepted_answers, :accepted_messages

  def initialize
  	@message_handler = MessageHandler.new
  	load_default_answers
  	load_default_messages  	  	
  end

  def hey(message)
  	@message_handler.message = message
  	answer	
  end

  def load_default_answers
  	@accepted_answers = { :shouting? => 'Woah, chill out!', :asking? => 'Sure.', :empty_message? => 'Fine. Be that way!' }
  	@accepted_answers.default = 'Whatever.'
  end

  def load_default_messages
  	@accepted_messages = @accepted_answers.keys
  end

  private
  
  def answer  	
  	@message_handler.accepted_types = @accepted_messages
  	@accepted_answers[@message_handler.type_of_message?]
  end 

end

class MessageHandler

  attr_accessor :accepted_types, :message

  def type_of_message?
	@accepted_types.find {|type| send(type)}
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
