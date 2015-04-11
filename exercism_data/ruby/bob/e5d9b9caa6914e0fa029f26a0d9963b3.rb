class Bob

  def hey(message)    
    respond_to listen(message)
  end
  
  private

   @@ANSWERS = {silence: 'Fine. Be that way!', shout: 'Woah, chill out!', question: 'Sure.', other: 'Whatever.'}
   
  def listen(message)
    if silence?(message)
      :silence
    elsif shout?(message)
      :shout
    elsif question?(message)
      :question
    else
      :other
    end        
  end
  
  def respond_to(heard)
    @@ANSWERS[heard]
  end
  
  def silence?(message)
    message =~ /\A\s*\Z/
  end
  
  def shout?(message)
    message.upcase == message && message.downcase != message
  end
  
  def question?(message)
    message.end_with?('?')
  end
  
end
