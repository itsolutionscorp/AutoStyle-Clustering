class Bob

  def hey(message)    
    respond_to Message.new(message)
  end
  
  private
  
  @@REACTIONS = {resentment: 'Fine. Be that way!', overwhelm: 'Woah, chill out!', condescension: 'Sure.', indifference: 'Whatever.'}        
  
  def respond_to(message)
    @@REACTIONS[interpret(message)]    
  end
  
  def interpret(message)
    if message.silence?
      :resentment
    elsif message.shout?
      :overwhelm
    elsif message.question?
      :condescension
    else
      :indifference
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
