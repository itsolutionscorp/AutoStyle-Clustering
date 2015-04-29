class Bob

  def hey(message)    
    respond_to Message.new(message)
  end
  
  private
  
  def respond_to(message)
    if message.silence?
      'Fine. Be that way!'        
    elsif message.shout?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

class Message
  
  attr_reader :content
  
  def initialize(content)
    @content = content
  end
    
  def silence?
    @content.strip.empty?
  end
  
  def shout?
    @content.upcase == @content && @content.downcase != @content
  end
  
  def question?
    @content.end_with?('?')
  end
  
end
