class Bob
  
  attr_accessor :message
  
  def hey(message)
    self.message = message
    
    if saying_nothing
      'Fine. Be that way!'
    elsif shouting
      'Woah, chill out!'
    elsif asking_a_question
      'Sure.'
    else
      'Whatever.'
    end
  end
  
  private

  def saying_nothing
    message.nil? || message.empty?
  end

  def asking_a_question
    message.end_with?('?')
  end
  
  def shouting
    message.upcase == message
  end
  

end
