class Bob

  def hey(message)    
    respond_to Message.new(message)
  end
  
  private
  
  @@ANSWERS = {silence: 'Fine. Be that way!', shout: 'Woah, chill out!', question: 'Sure.', other: 'Whatever.'}        
  
  def respond_to(message)
    @@ANSWERS[interpret(message)]    
  end
  
  def interpret(message)
    if message.silence?
      :silence
    elsif message.shout?
      :shout
    elsif message.question?
      :question
    else
      :other
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
