class Message
  def initialize(message)
    @message = message
  end
  
  def empty?
    @message.nil? or @message.strip.empty?
  end
  
  def forceful?
    @message == @message.upcase
  end
  
  def question?
    @message.end_with?'?'
  end
end
